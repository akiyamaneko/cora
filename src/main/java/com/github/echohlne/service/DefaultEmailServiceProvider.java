package com.github.echohlne.service;


import com.github.echohlne.config.EmailPropertyManager;
import com.github.echohlne.core.EmailContext;
import com.github.echohlne.core.IEmailServiceProvider;
import com.github.echohlne.exception.EmailRuntimeException;
import com.github.echohlne.util.Constants;
import com.github.echohlne.util.EmailUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class DefaultEmailServiceProvider implements IEmailServiceProvider, Serializable {
    private final transient String username, password;
    private final Session session;

    private DefaultEmailServiceProvider(String username, String password) {
        this.username = username;
        this.password = password;
        session = buildSession(EmailPropertyManager.loadDefaultProperties());
    }

    public static DefaultEmailServiceProvider build(String smtpHost, String username, String password) {
        return build(smtpHost, Constants.DEFAULT_SMTP_PORT, username, password);
    }


    public static DefaultEmailServiceProvider build(String smtpHost, int smtpPort, String username, String password) {
        return new DefaultEmailServiceProvider(username, password).serverConfig(smtpHost, smtpPort);
    }


    public DefaultEmailServiceProvider config(String key, String value) {
        session.getProperties().put(key, value);
        return this;
    }

    private DefaultEmailServiceProvider serverConfig(String host, int smtpPort) {
        session.getProperties().put("mail.smtp.host", host);
        session.getProperties().put("mail.smtp.port", smtpPort);
        return this;
    }


    public DefaultEmailServiceProvider connectionTimeoutMillSeconds(long milliseconds) {
        session.getProperties().put("mail.smtp.connectiontimeout", milliseconds);
        return this;
    }

    public DefaultEmailServiceProvider connectionTimeoutMillSeconds(long time, TimeUnit timeUnit) {

        session.getProperties().put("mail.smtp.connectiontimeout", EmailUtils.getMillSeconds(timeUnit, time));
        return this;
    }


    public DefaultEmailServiceProvider timeoutMilliSeconds(long milliseconds) {
        session.getProperties().put("mail.smtp.timeout", milliseconds);
        return this;
    }

    public DefaultEmailServiceProvider timeoutMilliSeconds(long time, TimeUnit timeUnit) {
        session.getProperties().put("mail.smtp.timeout", EmailUtils.getMillSeconds(timeUnit, time));
        return this;
    }


    private Session buildSession(Properties properties) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }


    @Override
    public void sendEmail(EmailContext emailContext) throws UnsupportedEncodingException {
        try {
            // 创建邮件对象
            Message message = new MimeMessage(session);

            //设置发件人
            message.setFrom(new InternetAddress(username));

            // 设置收件人
            message.setRecipients(Message.RecipientType.TO, EmailUtils.buildAddress(emailContext.getNormalReceiver()));
            // 设置抄送者
            message.setRecipients(Message.RecipientType.CC, EmailUtils.buildAddress(emailContext.getCopyReceiver()));
            // 设置秘密抄送者信息
            message.setRecipients(Message.RecipientType.BCC, EmailUtils.buildAddress(emailContext.getSecretReceiver()));

            // 设置邮件的主题
            message.setSubject(emailContext.getSubject());


            Multipart multiPart = new MimeMultipart();
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(emailContext.getBody(), "text/html;charset=utf-8");

            // 设置邮件的内容
            multiPart.addBodyPart(contentPart);
            for (String attachment : emailContext.getAttachments()) {
                BodyPart bodyPart = new MimeBodyPart();
                FileDataSource fileDataSource = new FileDataSource(attachment);
                bodyPart.setDataHandler(new DataHandler(fileDataSource));
                bodyPart.setFileName(MimeUtility.encodeText(fileDataSource.getName()));
                multiPart.addBodyPart(bodyPart);
            }
            message.setContent(multiPart);

            // 发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            throw new EmailRuntimeException(e);
        }
    }
}
