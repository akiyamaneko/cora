package com.github.echohlne.core;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public interface IEmailServiceProvider extends Serializable {
    void sendEmail(EmailContext emailContext) throws UnsupportedEncodingException;
}
