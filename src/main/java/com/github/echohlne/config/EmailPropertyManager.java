package com.github.echohlne.config;


import com.github.echohlne.util.Constants;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class EmailPropertyManager implements Serializable {

    private static final Map<String, Object> DEFAULT_PROPERTIES = new HashMap<>();

    static {
        DEFAULT_PROPERTIES.put("mail.transport.protocol", "smtp");
        DEFAULT_PROPERTIES.put("mail.smtp.auth", true);
        DEFAULT_PROPERTIES.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        DEFAULT_PROPERTIES.put("mail.smtp.socketFactory.fallback", "mail.smtp.socketFactory.fallback");
        DEFAULT_PROPERTIES.put("mail.smtp.port", Constants.DEFAULT_SMTP_PORT);
        DEFAULT_PROPERTIES.put("mail.smtp.connectiontimeout", 60 * 1000);
        DEFAULT_PROPERTIES.put("mail.smtp.timeout", 60 * 1000);
    }

    public static Properties loadDefaultProperties() {
        Properties properties = new Properties();
        properties.putAll(DEFAULT_PROPERTIES);

        return properties;
    }
}
