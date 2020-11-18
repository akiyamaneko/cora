package com.github.echohlne.exception;

import java.io.Serializable;

public class EmailRuntimeException extends RuntimeException implements Serializable {
    public EmailRuntimeException(String errorMessage) {
        super(errorMessage);
    }

    public EmailRuntimeException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public EmailRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public EmailRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
