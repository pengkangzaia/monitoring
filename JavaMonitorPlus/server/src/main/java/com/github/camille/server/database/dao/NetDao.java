package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.data.CPUEntity;
import com.github.camille.server.database.entity.data.NetEntity;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-02-16 15:35
 **/
@Component
public class NetDao {

    @Value("${influx.address}")
    public String url;

    @Value("${influx.token}")
    public String token;

    @Value("${influx.org}")
    public String org;

    @Value("${influx.bucket}")
    public String bucket;

    public void save(NetEntity netEntity) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        // 写数据
        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writeMeasurement(WritePrecision.MS, netEntity);
        client.close();
    }



}
