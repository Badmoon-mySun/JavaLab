package ru.kpfu.itis.javalab.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Profile("prod")
@Component
public class EmailUtilsImpl implements EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ExecutorService executorService;

    @Override
    public void sendMail(String to, String subject, String from, String text) {
        executorService.submit(() -> {
            javaMailSender.send(mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
                messageHelper.setSubject(subject);
                messageHelper.setFrom(from);
                messageHelper.setText(text, true);
                messageHelper.setTo(to);
            });
        });
    }
}
