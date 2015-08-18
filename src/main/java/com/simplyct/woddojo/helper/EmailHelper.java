package com.simplyct.woddojo.helper;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

/**
 * Created by cyril on 8/17/15.
 */
//@Service
public class EmailHelper {

    //@Autowired
    JavaMailSender javaMailSender;

    //@Autowired
    private TemplateEngine templateEngine;

    public void sendRegistrationConfirmation(final String emailTo,
                                             final String emailFrom,
                                             final String subject,
                                             final String confirmationId) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(emailTo);
            message.setFrom(emailFrom);
            message.setSubject(subject);

            final Context ctx = new Context(Locale.US);

            ctx.setVariable("confirmationId", confirmationId);
            String text = templateEngine.process("templates/emails/registration.html", ctx);
            message.setText(text, true);
        };
        javaMailSender.send(mimeMessagePreparator);
    }
}
