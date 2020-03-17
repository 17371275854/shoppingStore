package com.qf.store.shop.sendmail.service.controller;


import com.qf.dto.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("email")
public class SendMailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${activeServerUrl}")
    private String activeServerUrl;




    @RequestMapping("send/{address}/{uuid}")
    public ResultBean sendEmail(@PathVariable String address, @PathVariable String uuid){
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(message,true);
            messageHelper.setSubject("请激活注册的账号");

            //读取模板内容
            Context context = new Context();
            context.setVariable("username",address.substring(0,address.lastIndexOf('@')));
            context.setVariable("url",activeServerUrl+uuid);
            String mailInfo = templateEngine.process("mailmodel", context);
            //将模板内容放到邮件中
            messageHelper.setText(mailInfo,true);
            messageHelper.setFrom("3478787072@qq.com");
            messageHelper.setTo(address);

            sender.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return ResultBean.success("邮件发送成功!");
    }





}
