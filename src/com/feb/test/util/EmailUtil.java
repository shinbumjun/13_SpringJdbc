package com.feb.test.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.feb.test.dto.EmailDto;

@Component
public class EmailUtil {

	
	
    private JavaMailSender mailSender;
    public EmailUtil(JavaMailSender mailSender) { // xml
    	this.mailSender = mailSender;
    }

    
    
    public String sendMail(EmailDto email) {
        try {
        	System.out.println("mailSender1111111111111111111 :" + mailSender);
        	
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setTo(email.getReceiver());
            messageHelper.setText(email.getText());
            messageHelper.setFrom(email.getFrom());
            messageHelper.setSubject(email.getSubject());	// 메일제목은 생략이 가능하다

            mailSender.send(message);

        } catch(Exception e){
            System.out.println(e);
            return "Error";
        }
        return "Sucess";
    }
}
