package ru.kpfu.itis.javalab.utils;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
public interface EmailUtil {
    void sendMail(String to, String subject, String from, String text);
}
