package com.github.camille.server.alarm;

import lombok.Data;

import java.util.Date;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-19 13:24
 **/
@Data
public class MailEntity {

    private String from;
    private String replyTo;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private Date sentDate;
    private String subject;
    private String text;
    private String[] filenames;


}
