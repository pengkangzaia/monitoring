package com.github.camille.server.timer.job;

import com.github.camille.server.database.entity.Host;
import com.github.camille.server.database.entity.alarm.AlarmConditionConfig;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import com.github.camille.server.database.entity.alarm.AlarmEvent;
import com.github.camille.server.database.service.AlarmConfigService;
import com.github.camille.server.database.service.AlarmEventService;
import com.github.camille.server.database.service.HostService;
import com.github.camille.server.database.service.MetricService;
import com.github.camille.server.remote.util.HttpClient;
import com.github.camille.server.timer.util.TimerUtil;
import com.github.camille.server.util.ConditionDiagnosis;
import com.github.camille.server.util.MonitorConstant;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-18 19:00
 **/
public class PredictJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Value("${model.pred.url}")
    private String modelPredUrl;
    @Autowired
    private AlarmConfigService alarmConfigService;
    @Autowired
    private HostService hostService;
    @Autowired
    private AlarmEventService alarmEventService;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private MetricService metricService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        List<AlarmConfig> alarmConfigs = alarmConfigService.getAllConfig();
        if (CollectionUtils.isEmpty(alarmConfigs)) {
            throw new RuntimeException("没有配置要监控的远程主机策略");
        }
        logger.debug("predict data to check if abnormal...");
        for (AlarmConfig alarmConfig : alarmConfigs) {
            int hostId = alarmConfig.getHostId();
            // 判断当前是否有告警状态的AlarmEvent
            List<AlarmEvent> events = alarmEventService.getActiveEventByHostId(hostId);
            Host host = hostService.selectById(hostId);
            String address = "http://" + host.getIp() + ":" + host.getAgentPort();
            try {
                if (alarmConfig.isDynamic()) {
                    dynamicPred(alarmConfig, address, events);
                } else {
                    // 静态的
                    staticDetect(alarmConfig, address, events);
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            }


        }
    }

    private void staticDetect(AlarmConfig alarmConfig, String address, List<AlarmEvent> events) throws SchedulerException {
        List<AlarmConditionConfig> conditions = alarmConfigService.selectByConfigId(alarmConfig.getId());
        if (CollectionUtils.isNotEmpty(conditions)) {
            Map<String, AlarmEvent> eventMap = events.stream().
                    collect(Collectors.toMap(AlarmEvent::getMetricName, event -> event, (k1, k2) -> k1));
            for (AlarmConditionConfig condition : conditions) {
                String metric = condition.getMetric();
                String[] split = metric.split(MonitorConstant.underline);
                String measurement = split[0];
                String field = split[1];
                List<Double> values = metricService.selectByColumn(measurement, address, condition.getContinuePeriod(), field);
                diagnose(condition, values, eventMap, address, alarmConfig, metric);
            }
        }
    }


    public void dynamicPred(AlarmConfig alarmConfig, String address, List<AlarmEvent> events) throws SchedulerException {
        // 发送GET请求
        String response = HttpClient.doGet(modelPredUrl + "?ip=" + address);
        System.out.println("原响应：" + response);
        response = response.replaceAll("\r\n", "");
        logger.info("主机地址：" + address + " 模型反馈：" + response);
        Map<String, AlarmEvent> eventMap = events.stream().
                collect(Collectors.toMap(AlarmEvent::getMetricName, event -> event, (k1, k2) -> k1));
        if ("1".equals(response)) {
            if (eventMap.containsKey("dynamic")) {
                // 已有动态告警任务，不做处理
            } else {
                createAlarmJob(eventMap, address, alarmConfig, "dynamic", null);
            }
        } else if ("0".equals(response)) {
            deleteAlarmJob(eventMap, "dynamic");
        }
    }

    private void diagnose(AlarmConditionConfig condition, List<Double> values, Map<String, AlarmEvent> eventMap, String address, AlarmConfig alarmConfig, String metric) throws SchedulerException {
        boolean isAbnormal = ConditionDiagnosis.diagnose(condition.getOperator(), condition.getValue(), values);
        if (isAbnormal) {
            createAlarmJob(eventMap, address, alarmConfig, metric, condition.getNoticeFrequency());
        } else {
            deleteAlarmJob(eventMap, metric);
        }
    }

    private void deleteAlarmJob(Map<String, AlarmEvent> eventMap, String metric) throws SchedulerException {
        // 如果该主机对应的event有状态为正在告警的，需要设置无告警状态并且设置告警停止时间
        if (eventMap.containsKey(metric)) {
            AlarmEvent event = eventMap.get(metric);
            alarmEventService.releaseAlarm(event);
            // 停止Job
            String jobKey = "alarmJob" + event.getId();
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobKey)); // 暂停触发器
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey)); // 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobKey)); // 删除Job
            // todo 发送告警恢复消息
        }
    }


    private void createAlarmJob(Map<String, AlarmEvent> eventMap, String address, AlarmConfig alarmConfig, String metric, Integer noticeFreq) throws SchedulerException {
        if (eventMap.containsKey(metric)) {
            // 已有动态告警任务，不做处理
        } else {
            // 创建AlarmEvent
            AlarmEvent newEvent = new AlarmEvent();
            newEvent.setIsAlarm(1);
            newEvent.setHostId(alarmConfig.getHostId());
            newEvent.setContent(String.format(
                    "dynamic".equals(metric)
                            ? MonitorConstant.Dynamic_Alarm_Template
                            : MonitorConstant.Static_Alarm_Template
                    , address, alarmConfig.getName(), metric, TimerUtil.now())); // 告警内容
            newEvent.setMetricName(metric);
            // AlarmEvent落库
            alarmEventService.save(newEvent);
            // 创建告警任务
            JobDetail jobDetail = JobBuilder.newJob(AlarmJob.class)
                    .usingJobData("eventId", newEvent.getId())
                    .usingJobData("configId", alarmConfig.getId())
                    .withIdentity("alarmJob" + newEvent.getId())
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("alarmJob" + newEvent.getId())
                    .startNow()
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInSeconds(noticeFreq == null ? 3600 : noticeFreq)
                                    .repeatForever()
                    )
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        }
    }

}
