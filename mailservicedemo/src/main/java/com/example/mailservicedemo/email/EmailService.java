package com.example.mailservicedemo.email;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory

            .getLogger(EmailService.class);
    @Autowired
    private  JavaMailSender mailSender;



    @Override
    @Async
    public void send(String to, String email) {
//                    MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper =
//                    new MimeMessageHelper(mimeMessage, "utf-8");
//            helper.setText(email, true);
//            helper.setTo(to);
//            helper.setSubject("Confirm your email");
//            helper.setFrom("siripihemanth19@gmail.com");
//            mailSender.send(mimeMessage);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("siripihemanth19@gmail.com");
        message.setTo(to);
        message.setText(email);
        message.setSubject("Confirm Mail");
        mailSender.send(message);
        System.out.println("Mail Send...");
    }
}
