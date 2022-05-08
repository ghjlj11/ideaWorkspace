package com.ghj.springboot09test;

import org.apache.logging.log4j.message.SimpleMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.concurrent.Executor;

@SpringBootTest
class SpringBoot09TestApplicationTests {


    @Autowired
    JavaMailSenderImpl mailSender;
    @Test
    void contextLoads() {

        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        simpleMessage.setSubject("我是姜金征");
        simpleMessage.setText("来我办公室喝茶！");
        simpleMessage.setTo("2367792309@qq.com");
        simpleMessage.setFrom("2367792309@qq.com");
        mailSender.send(simpleMessage);

    }

    @Test
    public void test1() throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("是什么");
        helper.addAttachment("别点", new File("D:/Pictures/lj.jpg"));
        helper.setText("<h1 style='color: red'>你好呀</h1>", true);

        helper.setFrom("2367792309@qq.com");
        helper.setTo("2367792309@qq.com");
        mailSender.send(mimeMessage);
    }

}
