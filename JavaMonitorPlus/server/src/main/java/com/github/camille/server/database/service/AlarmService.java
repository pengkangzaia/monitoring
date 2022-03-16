package com.github.camille.server.database.service;

import com.github.camille.server.alarm.MailEntity;
import com.github.camille.server.database.entity.Host;
import com.github.camille.server.database.entity.alarm.AlarmConfig;
import com.github.camille.server.database.entity.user.User;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-06 8:31
 **/
@Service
public class AlarmService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailService mailService;
    @Autowired
    private HostService hostService;
    @Autowired
    private AlarmConfigService alarmConfigService;

    public void hostOffline(List<Integer> hostIds) {
        if (CollectionUtils.isNotEmpty(hostIds)) {
            List<Host> hosts = hostService.getHosts(hostIds);
            Map<Integer, String> hostMap = hosts.stream().collect(Collectors.toMap(Host::getId, Host::getName));
            // 没有配置告警的仅标注已下线
            // 配置了告警策略的发送给对应的用户
            List<AlarmConfig> configs = alarmConfigService.getConfigByHostIds(hostIds);
            if (CollectionUtils.isNotEmpty(configs)) {
                for (AlarmConfig config : configs) {
                    int hostId = config.getHostId();
                    if (hostMap.containsKey(config.getHostId())) {
                        String content = "主机: " + hostMap.get(hostId) + "\n agent已下线，请检查对应程序是否运行正常";
                        sendAlarmByEmail(content, config.getId());
                    }
                }
            }
        }
    }

    public void sendAlarmByEmail(String content, int alarmConfigId) {
        // 邮件报警
        MailEntity mail = new MailEntity();
        mail.setSentDate(new Date());
        List<User> noticeUser = alarmConfigService.getNoticeUser(alarmConfigId);
        String[] emails = new String[noticeUser.size()];
        for (int i = 0; i < noticeUser.size(); i++) {
            emails[i] = noticeUser.get(i).getEmail();
        }
        mail.setTo(emails);
        mail.setSubject("主机告警");
        mail.setText(content);
        mailService.sendSimpleMailMessage(mail);
    }


}
