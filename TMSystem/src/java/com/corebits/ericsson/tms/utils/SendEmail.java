
package com.corebits.ericsson.tms.utils;

import java.text.MessageFormat;
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
    final String password = tmsConfig.getValue("mail.password");
    final String mail_smtp_auth = tmsConfig.getValue("mail.smtp.auth");
    final String mail_smtp_starttls_enable = tmsConfig.getValue("mail.smtp.starttls.enable");
    final String mail_smtp_host = tmsConfig.getValue("mail.smtp.host");
    final String mail_smtp_port = tmsConfig.getValue("mail.smtp.port");
    final String mail_smtp_socketFactory_port = tmsConfig.getValue("mail.smtp.socketFactory.port");
    final String mail_smtp_socketFactory_class = tmsConfig.getValue("mail.smtp.socketFactory.class");
    String mail_account_department = tmsConfig.getValue("mail.account.department");
    String mail_template_loan_application_subject = tmsConfig.getValue("mail.template.loan.application.subject");
    String mail_template_loan_application = tmsConfig.getValue("mail.template.loan.application");
    String mail_template_loan_approval_subject = tmsConfig.getValue("mail.template.loan.approval.subject");
    String mail_template_loan_approval = tmsConfig.getValue("mail.template.loan.approval");
        
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
    
    public boolean SendMail(String recipientEmailAddress, String recipientName, EmailPurposeType emailPurposeType) {
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmailAddress));
            String txt;
            
            switch(emailPurposeType){
                case LOAN_APPLICATION:
                    message.setSubject(this.mail_template_loan_application_subject);
                    txt = MessageFormat.format(this.mail_template_loan_application, recipientName);
                    message.setText(txt);
                    break;
                case LOAN_APPROVAL:
                    message.setSubject(this.mail_template_loan_approval_subject);
                    txt = MessageFormat.format(this.mail_template_loan_approval, recipientName);
                    message.setText(txt);
                    break;                 
                default:
                    message.setSubject("Testing Subject");
                    message.setText("Dear Mail Crawler, \n\n No spam to my email, please!");
            }
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
