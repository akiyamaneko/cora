# Introduce
[中文文档](README_zh.md)  

cora is a concise, convenient and open source tool for sending emails using streaming API.
# Features
+ Concise API
+ Support attachment, cc, subject...
+ Support synchronous and asynchronous sending of emails
+ Support extension, currently implements `DefaultEmailServiceProvider` which can meet the sending function of most requirements.
 If `IEmailServiceProvider` is not satisfied, you can implement `IEmailServiceProvider` to develop your own service.

# Example

```java
                // set the connection timeout time to 60s, the timeout time to 50s, and specified the recipient and the CC.
               EmailBuilder.withEmailProvider(DefaultEmailServiceProvider.build("smtp.163.com", "gameending@163.com", "XXXXXXX")
                       .connectionTimeoutMillSeconds(60, TimeUnit.SECONDS).timeoutMilliSeconds(50, TimeUnit.SECONDS))
                       .withBody("email-by-cora")
                       .withSubject("email-by-cora-subject").withNormalReceiver("xxxxx@qq.com")
                       .withCopyReceiver("xxxxx@qq.com", "xxxx@163.com")
                       .send();
       
               // custom attachment.
               EmailBuilder.withEmailProvider(DefaultEmailServiceProvider.build("smtp.163.com", "gameending@163.com", "XXXXXXX"))
                       .withBody("email-by-cora")
                       .withSubject("email-by-cora-subject").withNormalReceiver("xxxxx@qq.com")
                       .withCopyReceiver("xxxxx@qq.com", "xxxx@163.com")
                       .withAttachments("/data/resources/scene.jpg")
                       .send();
       
               // custom attachment and send async.
               EmailBuilder.withEmailProvider(DefaultEmailServiceProvider.build("smtp.163.com", "gameending@163.com", "XXXXXXX"))
                       .withBody("email-by-cora")
                       .withSubject("email-by-cora-subject").withNormalReceiver("xxxxx@qq.com")
                       .withCopyReceiver("xxxxx@qq.com", "xxxx@163.com")
                       .asyncSend();
```

More API usage and extensions are welcome to explore