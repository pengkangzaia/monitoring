package com.github.camille.server.database.ts;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.time.Instant;
import java.util.List;
import java.util.Random;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-02-12 10:29
 **/
public class InfluxDB2Example {


    private static char[] token = "gZTu3-P2pKcGQI-wBgHUT1nRIckb7N_drF-r9YKUdbszy1hTrN3BwIR5CdFHshzGcW81n_SbjfI5-RQsUz11zA==".toCharArray();
    private static String org = "seu";
    private static String bucket = "monitor";

    public static void main(String[] args) throws InterruptedException {
        InfluxDBClient client = InfluxDBClientFactory.create("http://101.35.159.221:8086", token, org, bucket);
//        // 写数据
//        WriteApiBlocking writeApi = client.getWriteApiBlocking();
//
//        Double count = 1D;
//        while (true) {
//            System.out.println(System.currentTimeMillis());
//            Thread.sleep(1000);
//            CPU cpu = new CPU();
//            cpu.cpuUsage = 0.04122389153536094D + Math.random() * 100 + count;
//            cpu.fifteenMinuteLoad = 0.12D + Math.random() * 100 + count;
//            cpu.fiveMinuteLoad = 0.10D + Math.random() * 100 + count;
//            cpu.oneMinuteLoad = 0.15D + Math.random() * 100 + count;
//            cpu.address = "101.35.159.221:8081";
//            cpu.time = Instant.now();
//            count = count + 1D;
//            writeApi.writeMeasurement(WritePrecision.MS, cpu);
//        }

        // 查询数据
        String flux = "from(bucket:\"monitor\")" +
                " |> range(start: -1h) " +
                " |> filter(" +
                    "fn: (r) => r._measurement == \"cpu1\"" +
                ")";
        QueryApi queryApi = client.getQueryApi();
        long start = System.currentTimeMillis();
        List<FluxTable> tables = queryApi.query(flux);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        for (FluxTable table : tables) {
            List<FluxRecord> records = table.getRecords();
//            for (FluxRecord record : records) {
////                System.out.println(record);
//                System.out.println(record.getTime() + ":" + record.getValueByKey("_value"));
//            }
            FluxRecord record = records.get(0);
            System.out.println(record.getTime() + ":" + record.getValueByKey("_value"));

        }
        client.close();



    }

    @Measurement(name = "cpu1")
    private static class CPU {
        @Column(tag = true)
        String address;

        @Column
        Double cpuUsage;

        @Column
        Double fifteenMinuteLoad;

        @Column
        Double fiveMinuteLoad;

        @Column
        Double oneMinuteLoad;


        @Column(timestamp = true)
        Instant time;
    }


}
