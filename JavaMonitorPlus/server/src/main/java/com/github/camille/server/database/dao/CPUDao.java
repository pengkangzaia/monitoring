package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.data.CPUEntity;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 8:57
 **/
@Component
public class CPUDao {

    @Value("${influx.address}")
    public String url;

    @Value("${influx.token}")
    public String token;

    @Value("${influx.org}")
    public String org;

    @Value("${influx.bucket}")
    public String bucket;


    public List<CPUEntity> findAllByAddress(String address) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        String flux = "from(bucket:\"monitor\")" +
                " |> range(start: -1mo) " +
                " |> filter(" +
                "fn: (r) => r._measurement == \"cpu2\" and r.address ==\"" + address +
                "\")" +
                "|> pivot(rowKey: [\"_time\"], columnKey: [\"_field\"], valueColumn: \"_value\")";
        QueryApi queryApi = client.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux);
        List<CPUEntity> res = new ArrayList<>();
        FluxTable table = tables.get(0);
        List<FluxRecord> records = table.getRecords();
        for (FluxRecord record : records) {
            CPUEntity cpuEntity = new CPUEntity();
            cpuEntity.setDate(record.getTime());
            cpuEntity.setAddress((String) record.getValueByKey("address"));
            cpuEntity.setCpuUsage((Double) record.getValueByKey("cpuUsage"));
            cpuEntity.setOneMinuteLoad((Double) record.getValueByKey("oneMinuteLoad"));
            cpuEntity.setFiveMinuteLoad((Double) record.getValueByKey("fiveMinuteLoad"));
            cpuEntity.setFifteenMinuteLoad((Double) record.getValueByKey("fifteenMinuteLoad"));
            res.add(cpuEntity);
        }
        client.close();
        return res;
    }

    public void save(CPUEntity cpuEntity) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        // 写数据
        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writeMeasurement(WritePrecision.MS, cpuEntity);
        client.close();
    }

    public void deleteAll() {

    }

    public List<Double> selectByColumn(String address, Integer limit, String columnName) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        String flux = "from(bucket: \"monitor\")\n" +
                "  |> range(start: -1mo)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"cpu2\")\n" +
                "  |> filter(fn: (r) => r[\"address\"] == \"" + address + "\")\n" +
                "  |> filter(fn: (r) => r[\"_field\"] == \"" + columnName + "\")" +
                "  |> limit(n: " + limit + ")";
        QueryApi queryApi = client.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux);
        List<Double> res = new ArrayList<>();
        FluxTable table = tables.get(0);
        List<FluxRecord> records = table.getRecords();
        for (FluxRecord record : records) {
            res.add((Double) record.getValue());
        }
        client.close();
        return res;
    }
}
