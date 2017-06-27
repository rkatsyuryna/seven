package com.seven.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by ruslankatsyuryna on 6/27/17.
 */
@Service
public class NotificationServiceImpl {

    @Autowired
    private  JavaMailSender emailSender;

    @Async
    public void sendNotificaitoin(String to, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Hello, this is your password for app");
        message.setText(password);
        emailSender.send(message);
    }
}
