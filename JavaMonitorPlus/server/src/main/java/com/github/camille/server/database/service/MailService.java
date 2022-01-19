package com.github.camille.server.database.service;


import com.github.camille.server.alarm.MailEntity;
import com.github.camille.server.alarm.param.MailParam;
import com.github.dozermapper.core.Mapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

/**
 * @author pengkangzaia@foxmail.com
 * @create 2022-01-19 13:24
 **/
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailParam mailParam;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Mapper mapper;

    public void sendSimpleMailMessage(MailEntity mailEntity) {
        if (StringUtils.isEmpty(mailEntity.getFrom())) {
            mailEntity.setFrom(mailParam.getFrom());
        }
        SimpleMailMessage simpleMailMessage = mapper.map(mailEntity, SimpleMailMessage.class);
        javaMailSender.send(simpleMailMessage);
    }

    public void sendMimeMessage(MailEntity MailEntity) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true);

            if (StringUtils.isEmpty(MailEntity.getFrom())) {
                messageHelper.setFrom(mailParam.getFrom());
            }
            messageHelper.setTo(MailEntity.getTo());
            messageHelper.setSubject(MailEntity.getSubject());

            mimeMessage = messageHelper.getMimeMessage();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(MailEntity.getText(), "text/html;charset=UTF-8");

            // 描述数据关系
            MimeMultipart mm = new MimeMultipart();
            mm.setSubType("related");
            mm.addBodyPart(mimeBodyPart);

            // 添加邮件附件
            for (String filename : MailEntity.getFilenames()) {
                MimeBodyPart attachPart = new MimeBodyPart();
                try {
                    attachPart.attachFile(filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mm.addBodyPart(attachPart);
            }
            mimeMessage.setContent(mm);
            mimeMessage.saveChanges();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }


}
