package com.github.camille.server.database.dao;

import com.github.camille.server.core.entity.DiskEntity;
import com.github.camille.server.database.entity.data.CPUEntity;
import com.github.camille.server.database.entity.data.HardDiskEntity;
import com.github.camille.server.database.entity.data.MemEntity;
import com.github.camille.server.database.entity.statistic.MinMaxDiskMetric;
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
 * @create 2022-01-16 13:30
 **/
@Component
public class DiskDao {

    @Value("${influx.address}")
    public String url;

    @Value("${influx.token}")
    public String token;

    @Value("${influx.org}")
    public String org;

    @Value("${influx.bucket}")
    public String bucket;

    public List<HardDiskEntity> findAllByAddress(String address) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        String flux = "from(bucket:\"monitor\")" +
                " |> range(start: -1mo) " +
                " |> filter(" +
                "fn: (r) => r._measurement == \"disk\" and r.address ==\"" + address +
                "\")" +
                "|> pivot(rowKey: [\"_time\"], columnKey: [\"_field\"], valueColumn: \"_value\")";
        QueryApi queryApi = client.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux);
        List<HardDiskEntity> res = new ArrayList<>();
        FluxTable table = tables.get(0);
        List<FluxRecord> records = table.getRecords();
        for (FluxRecord record : records) {
            HardDiskEntity diskEntity = new HardDiskEntity();
            diskEntity.setDate(record.getTime());
            diskEntity.setAddress((String) record.getValueByKey("address"));
            diskEntity.setRio((Double) record.getValueByKey("rio"));
            diskEntity.setWio((Double) record.getValueByKey("wio"));
            diskEntity.setRkb((Double) record.getValueByKey("rkb"));
            diskEntity.setWkb((Double) record.getValueByKey("wkb"));
            diskEntity.setRAwait((Double) record.getValueByKey("rAwait"));
            diskEntity.setWAwait((Double) record.getValueByKey("wAwait"));
            diskEntity.setSvctm((Double) record.getValueByKey("svctm"));
            diskEntity.setUtil((Double) record.getValueByKey("util"));
            res.add(diskEntity);
        }
        client.close();
        return res;
    }

    public void save(HardDiskEntity hardDiskEntity) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        // 写数据
        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writeMeasurement(WritePrecision.MS, hardDiskEntity);
        client.close();
    }

    public void deleteAll() {

    }

    public MinMaxDiskMetric selectMinMaxMetricByAddress(String address) {
        return null;
    }

    public List<HardDiskEntity> selectLimitByAddress(String address, int limit) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        String flux = "from(bucket: \"monitor\")\n" +
                "  |> range(start: -1mo)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"disk\")\n" +
                "  |> filter(fn: (r) => r[\"address\"] == \"" + address + "\")\n" +
                "  |> limit(n: " + limit + ")" +
                "  |> pivot(rowKey: [\"_time\"], columnKey: [\"_field\"], valueColumn: \"_value\")";
        QueryApi queryApi = client.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux);
        List<HardDiskEntity> res = new ArrayList<>();
        FluxTable table = tables.get(0);
        List<FluxRecord> records = table.getRecords();
        for (FluxRecord record : records) {
            HardDiskEntity diskEntity = new HardDiskEntity();
            diskEntity.setDate(record.getTime());
            diskEntity.setAddress((String) record.getValueByKey("address"));
            diskEntity.setRio((Double) record.getValueByKey("rio"));
            diskEntity.setWio((Double) record.getValueByKey("wio"));
            diskEntity.setRkb((Double) record.getValueByKey("rkb"));
            diskEntity.setWkb((Double) record.getValueByKey("wkb"));
            diskEntity.setRAwait((Double) record.getValueByKey("rAwait"));
            diskEntity.setWAwait((Double) record.getValueByKey("wAwait"));
            diskEntity.setSvctm((Double) record.getValueByKey("svctm"));
            diskEntity.setUtil((Double) record.getValueByKey("util"));
            res.add(diskEntity);
        }
        client.close();
        return res;
    }

    public List<Double> selectByColumn(String address, Integer limit, String columnName) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        String flux = "from(bucket: \"monitor\")\n" +
                "  |> range(start: -1mo)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"disk\")\n" +
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
