package com.github.echohlne.core;


import com.github.echohlne.exception.EmailRuntimeException;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class EmailBuilder implements Serializable {

    private final EmailContext miniEmailContext;
    private IEmailServiceProvider emailServiceProvider;

    private EmailBuilder() {
        miniEmailContext = new EmailContext();
    }

    public static EmailBuilder withEmailProvider(IEmailServiceProvider provider) {
        EmailBuilder bootstrap = new EmailBuilder();
        bootstrap.emailServiceProvider = provider;
        return bootstrap;
    }

    public EmailBuilder withSubjectAndBody(String subject, String body) {
        miniEmailContext.setSubject(subject);
        miniEmailContext.setBody(body);
        return this;
    }

    public EmailBuilder withAttachments(String... attachments) {
        miniEmailContext.setAttachments(Arrays.asList(attachments));
        return this;
    }

    public EmailBuilder withSubject(String subject) {
        miniEmailContext.setSubject(subject);
        return this;
    }

    public EmailBuilder withBody(String body) {
        miniEmailContext.setBody(body);
        return this;
    }

    public EmailBuilder withNormalReceiver(String... normalReceiver) {
        miniEmailContext.setNormalReceiver(Arrays.asList(normalReceiver));
        return this;
    }


    public EmailBuilder withCopyReceiver(String... copyReceiver) {
        miniEmailContext.setCopyReceiver(Arrays.asList(copyReceiver));
        return this;
    }

    private EmailBuilder withSecretReceiver(String... secretReceiver) {
        miniEmailContext.setSecretReceiver(Arrays.asList(secretReceiver));
        return this;
    }


    public void send() {
        try {
            emailServiceProvider.sendEmail(this.miniEmailContext);
            System.out.println("Send email success.");
        } catch (UnsupportedEncodingException e) {
            throw new EmailRuntimeException(e);
        }
    }

    public void asyncSend() {
        CompletableFuture<Void> voidCompletableFuture =
                CompletableFuture.runAsync(() -> {
                    try {
                        emailServiceProvider.sendEmail(this.miniEmailContext);
                    } catch (UnsupportedEncodingException e) {
                        throw new EmailRuntimeException(e);
                    }
                });
        voidCompletableFuture.join();
    }

}
