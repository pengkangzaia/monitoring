package com.github.camille.server.database.service;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-03-04 11:33
 **/
@Service
public class MetricService {

    @Value("${influx.address}")
    public String url;

    @Value("${influx.token}")
    public String token;

    @Value("${influx.org}")
    public String org;

    @Value("${influx.bucket}")
    public String bucket;


    public List<Double> selectByColumn(String measurement, String address, Integer limit, String columnName) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        String flux = "from(bucket: \"monitor\")\n" +
                "  |> range(start: -1mo)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"" + measurement + "\")\n" +
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
