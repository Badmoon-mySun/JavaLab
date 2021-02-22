package ru.kpfu.itis.javalab.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Slf4j
@Profile("dev")
@Component
public class FakeEmailUtilsImpl implements EmailUtil {

    @Override
    public void sendMail(String to, String subject, String from, String text) {
//        log.info("send message from" + from + " to " + to + " subject - " + subject + " body:\n" + text);
        System.out.println("send message from" + from + " to " + to + " subject - " + subject + " body:\n" + text);
    }
}
