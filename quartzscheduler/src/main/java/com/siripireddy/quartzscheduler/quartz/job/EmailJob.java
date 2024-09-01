package com.siripireddy.quartzscheduler.quartz.job;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
//@Slf4j
public class EmailJob extends QuartzJobBean {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap=context.getMergedJobDataMap();
        String subject=dataMap.getString("subject");
        String body=dataMap.getString("body");
        String toMail=dataMap.getString("email");

        sendEmail(mailProperties.getUsername(),toMail,subject,body);

    }
    protected void sendEmail(String from, String to, String subject, String body){

        try{
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.toString());
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);

            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException m){
           // log.error("error sending mail");
        }

    }
}
