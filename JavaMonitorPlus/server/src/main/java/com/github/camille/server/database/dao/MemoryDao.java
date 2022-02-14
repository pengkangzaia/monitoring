package com.github.camille.server.database.dao;

import com.github.camille.server.core.entity.MemoryEntity;
import com.github.camille.server.database.entity.data.CPUEntity;
import com.github.camille.server.database.entity.data.MemEntity;
import com.github.camille.server.database.entity.statistic.MinMaxMemMetric;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-01 9:00
 **/
@Component
public class MemoryDao {

    @Value("${influx.address}")
    public String url;

    @Value("${influx.token}")
    public String token;

    @Value("${influx.org}")
    public String org;

    @Value("${influx.bucket}")
    public String bucket;

    public List<MemEntity> findAllByAddress(String address) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        String flux = "from(bucket:\"monitor\")" +
                " |> range(start: -1mo) " +
                " |> filter(" +
                "fn: (r) => r._measurement == \"memory\" and r.address ==\"" + address +
                "\")" +
                "|> pivot(rowKey: [\"_time\"], columnKey: [\"_field\"], valueColumn: \"_value\")";
        QueryApi queryApi = client.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux);
        List<MemEntity> res = new ArrayList<>();
        FluxTable table = tables.get(0);
        List<FluxRecord> records = table.getRecords();
        for (FluxRecord record : records) {
            MemEntity memEntity = new MemEntity();
            memEntity.setDate(record.getTime());
            memEntity.setAddress((String) record.getValueByKey("address"));
            memEntity.setUsed((Double) record.getValueByKey("used"));
            memEntity.setUsedPercent((Double) record.getValueByKey("usedPercent"));
            res.add(memEntity);
        }
        client.close();
        return res;
    }

    public void save(MemEntity memEntity) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        // 写数据
        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writeMeasurement(WritePrecision.MS, memEntity);
        client.close();
    }

    public void deleteAll() {

    }

    public MinMaxMemMetric selectMinMaxMetricByAddress(String address) {
        return null;
    }

    public List<MemEntity> selectLimitByAddress(String address, int limit) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        String flux = "from(bucket: \"monitor\")\n" +
                "  |> range(start: -1mo)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"memory\")\n" +
                "  |> filter(fn: (r) => r[\"address\"] == \"" + address + "\")\n" +
                "  |> limit(n: " + limit + ")" +
                "  |> pivot(rowKey: [\"_time\"], columnKey: [\"_field\"], valueColumn: \"_value\")";
        QueryApi queryApi = client.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux);
        List<MemEntity> res = new ArrayList<>();
        FluxTable table = tables.get(0);
        List<FluxRecord> records = table.getRecords();
        for (FluxRecord record : records) {
            MemEntity memEntity = new MemEntity();
            memEntity.setDate(record.getTime());
            memEntity.setAddress((String) record.getValueByKey("address"));
            memEntity.setUsed((Double) record.getValueByKey("used"));
            memEntity.setUsedPercent((Double) record.getValueByKey("usedPercent"));
            res.add(memEntity);
        }
        client.close();
        return res;
    }

    public List<Double> selectByColumn(String address, Integer limit, String columnName) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        String flux = "from(bucket: \"monitor\")\n" +
                "  |> range(start: -1mo)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"memory\")\n" +
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
