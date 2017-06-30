package com.seven.services.impl;

import com.seven.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by ruslankatsyuryna on 6/27/17.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private  JavaMailSender emailSender;

    @Async
    @Override
    public void sendNotification(String to, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Hello, this is your password for app");
        message.setText(password);
        emailSender.send(message);
    }
}
