package com.eiit.presystemeiit.utils;

import com.eiit.presystemeiit.config.PropertiesConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Component
public class EmailUtil {


    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    PropertiesConfigure configure;

    public void sendSimpleMailCode(String email, Long code) throws MailException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("Ekvision用户找回密码验证码");
        helper.setFrom("ekvision@yeah.net");
        helper.setTo(email);
        helper.setSentDate(new Date());

        //获取文件路径
        java.net.URL uri = this.getClass().getResource("/");

        helper.setText("<img src='cid:p01' width='75px' height='75px' /><br /><br />您好, 您本次操作的验证码为： " + code + " , 请勿将验证码告知他人, 以免造成不必要的损失.", true);
        helper.addInline("p01", new FileSystemResource(new File( configure.customerMkdirPath + "/system/" +"ekvision.jpg")));

        helper.setCc(InternetAddress.parse("ekvision@yeah.net"));
        javaMailSender.send(mimeMessage);
    }

    public void sendResetPasswordSimpleMailCode(String email, Long code) throws MailException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("Ekvision广告机发布系统用户找回密码验证码");
        helper.setFrom("ekvision@yeah.net");
        helper.setTo(email);
        helper.setSentDate(new Date());

        helper.setText("感谢您的使用，您本次操作的验证码为：" + code + ", 请勿将验证码告知他人, 以免造成不必要的损失。");

        helper.setCc(InternetAddress.parse("ekvision@yeah.net"));
        javaMailSender.send(mimeMessage);
    }

    public void sendSimpleMailResetPassword(String email, String urlAccess, String token) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("注册用户验证码");
        helper.setFrom("ekvision@yeah.net");
        helper.setTo(email);
        helper.setSentDate(new Date());

        //获取文件路径
        java.net.URL uri = this.getClass().getResource("/");

        helper.setText("<img src='cid:p01' width='75px' height='75px' /><br /><br />请点击以下连接重置密码：<br /><br /><a href='"+urlAccess+"/account/reset_url/"+token+"' target='_brank'>"+urlAccess+"/account/"+token+"</a><br /><br /><br /><br />如果不是您本人操作, 请忽略.", true);
        helper.addInline("p01", new FileSystemResource(new File( configure.customerMkdirPath + "/system/" +"ekvision.jpg")));

        javaMailSender.send(mimeMessage);
    }
}
