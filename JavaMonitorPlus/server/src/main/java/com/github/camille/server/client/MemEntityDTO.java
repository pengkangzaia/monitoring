package com.github.camille.server.client;

import lombok.Data;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2021-11-06下午5:14
 **/
@Data
public class MemEntityDTO {

    private String used;
    private String usedPercent;

    public MemEntityDTO(String used, String usedPercent) {
        this.used = used;
        this.usedPercent = usedPercent;
    }
}
