/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Tommy
 */
public class SendEmail {

    public static void main(String[] args) {
        SendEmail se = new SendEmail();
        System.out.println("Response TLS : " + se.SendMailTLS());
//        System.out.println("Response SSL : " + se.SendMailSSL());
    }

    public boolean SendMailTLS() {

        final String username = "gentletom2004@gmail.com";
        final String password = "nicola1982$*T";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gentletom2004@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("gentletom2004@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler, \n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean SendMailSSL() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gentletom2004@gmail.com", "...........");
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gentletom2004@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("gentletom2004@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler, \n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
