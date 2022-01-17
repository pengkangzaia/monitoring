package com.github.onblog.client.core.entity;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-06下午5:14
 **/
@Data
public class MemoryEntity {

    private String used;
    private String usedPercent;

    public MemoryEntity(String used, String usedPercent) {
        this.used = used;
        this.usedPercent = usedPercent;
    }
}
