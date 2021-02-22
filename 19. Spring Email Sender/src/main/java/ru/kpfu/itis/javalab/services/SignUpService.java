package ru.kpfu.itis.javalab.services;

import ru.kpfu.itis.javalab.dto.UserForm;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
public interface SignUpService {
    void signUp(UserForm userForm);
    boolean confirm(String code);
}
