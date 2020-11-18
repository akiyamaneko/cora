package com.github.echohlne.util;


import com.github.echohlne.exception.EmailRuntimeException;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class EmailUtils implements Serializable {
    private static final Address[] EMPTY_ADDRESS = {};

    private EmailUtils() {
    }

    public static Address[] buildAddress(List<String> notifiers) {
        if (notifiers.isEmpty()) {
            return EMPTY_ADDRESS;
        }

        Address[] addressAfterBuild = new Address[notifiers.size()];
        try {
            for (int i = 0; i < notifiers.size(); i++) {
                addressAfterBuild[i] = new InternetAddress(notifiers.get(i));
            }
        } catch (AddressException e) {
            throw new EmailRuntimeException(e);
        }

        return addressAfterBuild;
    }

    public static long getMillSeconds(TimeUnit timeUnit, long time) {
        long milliseconds = -1L;
        switch (timeUnit) {
            case DAYS:
                milliseconds = time * 24 * 60 * 60 * 1000;
                break;
            case HOURS:
                milliseconds = time * 60 * 60 * 1000;
                break;
            case MINUTES:
                milliseconds = time * 60 * 1000;
                break;
            case SECONDS:
                milliseconds = time * 1000;
                break;
            case MILLISECONDS:
                milliseconds = time;
                break;
            case MICROSECONDS:
                milliseconds = time / 1000;
                break;

        }
        if(milliseconds < 0) {
            throw new EmailRuntimeException(String.format("milliseconds value is wrong, expected non-negative value, but got:%s\n", milliseconds));
        }
        return milliseconds;
    }
}
