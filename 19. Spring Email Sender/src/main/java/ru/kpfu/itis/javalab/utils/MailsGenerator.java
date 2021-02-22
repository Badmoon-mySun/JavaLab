package ru.kpfu.itis.javalab.utils;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String confirmCode);
}
