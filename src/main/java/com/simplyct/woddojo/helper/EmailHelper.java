package com.simplyct.woddojo.helper;

import com.simplyct.woddojo.helper.dto.EmailDto;
import com.simplyct.woddojo.helper.dto.MessageUs;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.Locale;

/**
 * Created by cyril on 8/17/15.
 */
@Service("emailHelper")
public class EmailHelper {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${woddojo.admin.email,from}")
    private String ADMIN_EMAIL;

    @Async
    public void sendRegistrationConfirmation(EmailDto emailDto) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(emailDto.getEmailTo());
            message.setFrom(emailDto.getEmailFrom());
            message.setSubject(emailDto.getSubject());

            final Context ctx = new Context(Locale.US);

            ctx.setVariable("emailDto", emailDto);
            String text = templateEngine.process("emails/registration", ctx);
            message.setText(text, true);
        };
        javaMailSender.send(mimeMessagePreparator);
    }

    @Async
    public void sendSimpleEmail(EmailDto emailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailDto.getEmailFrom());
        simpleMailMessage.setTo(emailDto.getEmailTo());
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setSubject(emailDto.getSubject());
        simpleMailMessage.setText(emailDto.getBody());
        javaMailSender.send(simpleMailMessage);
    }

    public void sendWelcomeEmail(User user, Organization organization) {
        EmailDto emailDto = new EmailDto(organization.getEmail(), user.getEmail(), Constants.WELCOME_EMAIL_SUBJECT);
        emailDto.setOrgName(organization.getName());
        emailDto.setUserName(user.getFirstName());
        emailDto.setLogoUrl(organization.getName());
        sendRegistrationConfirmation(emailDto);
    }

    public void sendMessageUsEmail(MessageUs messageUs, String orgEmail) {
        String subject = String.format("%s has a message for you", messageUs.getName());
        EmailDto emailDto = new EmailDto(orgEmail, ADMIN_EMAIL, subject);
        String message = String.format("Here is a message from : %s \n\n" +
                                               "%s \n\n" +
                                               "Email: %s \n" +
                                               "Phone: %s",
                                       messageUs.getName(), messageUs.getMessage(), messageUs.getEmail(), messageUs.getPhone());
        emailDto.setBody(message);

        sendSimpleEmail(emailDto);
    }


}
