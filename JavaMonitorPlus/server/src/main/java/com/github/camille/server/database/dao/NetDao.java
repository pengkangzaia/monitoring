package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.data.CPUEntity;
import com.github.camille.server.database.entity.data.MemEntity;
import com.github.camille.server.database.entity.data.NetEntity;
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


    public List<NetEntity> findAllByAddress(String address) {
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
        String flux = "from(bucket:\"monitor\")" +
                " |> range(start: -1mo) " +
                " |> filter(" +
                "fn: (r) => r._measurement == \"net\" and r.address ==\"" + address +
                "\")" +
                "|> pivot(rowKey: [\"_time\"], columnKey: [\"_field\"], valueColumn: \"_value\")";
        QueryApi queryApi = client.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux);
        List<NetEntity> res = new ArrayList<>();
        FluxTable table = tables.get(0);
        List<FluxRecord> records = table.getRecords();
        for (FluxRecord record : records) {
            NetEntity netEntity = new NetEntity();
            netEntity.setDate(record.getTime());
            netEntity.setAddress((String) record.getValueByKey("address"));
            netEntity.setReceivePacket((Double) record.getValueByKey("receivePacket"));
            netEntity.setSendPacket((Double) record.getValueByKey("sendPacket"));
            netEntity.setReceive((Double) record.getValueByKey("receive"));
            netEntity.setSend((Double) record.getValueByKey("send"));
            netEntity.setTcpCount(Integer.parseInt(String.valueOf(record.getValueByKey("tcpCount"))));
            res.add(netEntity);
        }
        client.close();
        return res;
    }
}
