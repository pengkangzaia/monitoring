package com.github.camille.server.alarm.param;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-19 13:22
 **/
@Validated
@Component
@Data
@ConfigurationProperties(prefix = "mail")
public class MailParam {

    private String domain;
    private String from;


}

