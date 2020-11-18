# 概述
[英文文档](README.md)  

cora是一个简洁，方便并且使用流式API发送邮件的开源工具库
# 特性
+ 简洁、方便的API
+ 支持抄送、附件
+ 支持异步发送
+ 支持扩展,目前实现了`DefaultEmailServiceProvider`可以满足大部分邮箱的发送功能，如果`DefaultEmailServiceProvider`不满足可以实现`IEmailServiceProvider`开发自己的业务类
# 使用举例

```java
        // 自定义超时时间，指定了收件人,抄送人
        EmailBuilder.withEmailProvider(DefaultEmailServiceProvider.build("smtp.163.com", "gameending@163.com", "XXXXXXX")
                .connectionTimeoutMillSeconds(60, TimeUnit.SECONDS).timeoutMilliSeconds(50, TimeUnit.SECONDS))
                .withBody("email-by-cora")
                .withSubject("email-by-cora-subject").withNormalReceiver("xxxxx@qq.com")
                .withCopyReceiver("xxxxx@qq.com", "xxxx@163.com")
                .send();

        // 自定义附件
        EmailBuilder.withEmailProvider(DefaultEmailServiceProvider.build("smtp.163.com", "gameending@163.com", "XXXXXXX"))
                .withBody("email-by-cora")
                .withSubject("email-by-cora-subject").withNormalReceiver("xxxxx@qq.com")
                .withCopyReceiver("xxxxx@qq.com", "xxxx@163.com")
                .withAttachments("/data/resources/scene.jpg")
                .send();

        // 自定义附件异步发哦是哪个
        EmailBuilder.withEmailProvider(DefaultEmailServiceProvider.build("smtp.163.com", "gameending@163.com", "XXXXXXX"))
                .withBody("email-by-cora")
                .withSubject("email-by-cora-subject").withNormalReceiver("xxxxx@qq.com")
                .withCopyReceiver("xxxxx@qq.com", "xxxx@163.com")
                .asyncSend();
```
更多API的用法还有扩展欢迎探索
