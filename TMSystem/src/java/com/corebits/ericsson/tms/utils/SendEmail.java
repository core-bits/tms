
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
    TMSConfiguration tmsConfig = TMSConfiguration.getInstance();

    final String username = tmsConfig.getValue("mail.from");
    final String password = tmsConfig.getValue("mail.from.password");
    final String mail_smtp_auth = tmsConfig.getValue("mail.smtp.auth");
    final String mail_smtp_starttls_enable = tmsConfig.getValue("mail.smtp.starttls.enable");
    final String mail_smtp_host = tmsConfig.getValue("mail.smtp.host");
    final String mail_smtp_port = tmsConfig.getValue("mail.smtp.port");
    final String mail_smtp_socketFactory_port = tmsConfig.getValue("mail.smtp.socketFactory.port");
    final String mail_smtp_socketFactory_class = tmsConfig.getValue("mail.smtp.socketFactory.class");
        
    public static void main(String[] args) {
        SendEmail se = new SendEmail();
        System.out.println("Response TLS : " + se.SendMailTLS());
//        System.out.println("Response SSL : " + se.SendMailSSL());
    }

    public boolean SendMailTLS() {

        

        Properties props = new Properties();
        props.put("mail.smtp.auth", mail_smtp_auth);
        props.put("mail.smtp.starttls.enable", mail_smtp_starttls_enable);
        props.put("mail.smtp.host", mail_smtp_host);
        props.put("mail.smtp.port", mail_smtp_port);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
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
        props.put("mail.smtp.host", mail_smtp_host);
        props.put("mail.smtp.socketFactory.port", mail_smtp_socketFactory_port);
        props.put("mail.smtp.socketFactory.class", mail_smtp_socketFactory_class);
        props.put("mail.smtp.auth", mail_smtp_auth);
        props.put("mail.smtp.port", mail_smtp_socketFactory_port);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
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
