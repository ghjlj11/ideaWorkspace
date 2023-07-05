package com.ghj.springcloud.service.impl;

import com.ghj.springcloud.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author guohuanjun1
 * @date 2023/7/5
 */
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {

    @Resource
    MessageChannel output;

    @Override
    public String send() {
        String s = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(s).build());
        System.out.println("发出消息:" + s );
        return s;
    }
}
