package com.github.echohlne.core;

import com.github.echohlne.util.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class EmailContext  implements Serializable {
    private List<String> normalReceiver = new ArrayList<>();
    private List<String> copyReceiver = new ArrayList<>();
    private List<String> secretReceiver = new ArrayList<>();
    private String subject = Constants.DEFAULT_EMAIL_SUBJECT;
    private String body = "";
    private List<String> attachments = new ArrayList<>();

    public List<String> getNormalReceiver() {
        return normalReceiver;
    }

    public void setNormalReceiver(List<String> normalReceiver) {
        this.normalReceiver = normalReceiver;
    }

    public List<String> getCopyReceiver() {
        return copyReceiver;
    }

    public void setCopyReceiver(List<String> copyReceiver) {
        this.copyReceiver = copyReceiver;
    }

    public List<String> getSecretReceiver() {
        return secretReceiver;
    }

    public void setSecretReceiver(List<String> secretReceiver) {
        this.secretReceiver = secretReceiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }
}
