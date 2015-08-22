package com.simplyct.woddojo.helper;

import com.simplyct.woddojo.helper.dto.EmailDto;
import com.simplyct.woddojo.model.Organization;
import com.simplyct.woddojo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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


    public void sendWelcomeEmail(User user, Organization organization) {
        EmailDto emailDto = new EmailDto(organization.getEmail(), user.getEmail(), Constants.WELCOME_EMAIL_SUBJECT);
        emailDto.setOrgName(organization.getName());
        emailDto.setUserName(user.getFirstName());
        emailDto.setLogoUrl(organization.getName());
        sendRegistrationConfirmation(emailDto);
    }
}
