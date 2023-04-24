package com.email.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String to, String subject, String text) {
        //flag to track success of method
        boolean flag = false;

        //Email id of Sender
        String from = "aarushshah8854@gmail.com";

        //Host for Gmail
        String host = "smtp.gmail.com";

        //Configure SMTP server details using the Java Properties object
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        /*Create Session with getInstance() and pass props and
        Authenticator and override getPasswordAuthentication() method*/
        String username = "aarushshah8854";
        String password = "hsowwsvbnabdjhzf";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        //Create message using MimeMessage
        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);

            //Send mail using Transport class
            Transport.send(message);

            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
