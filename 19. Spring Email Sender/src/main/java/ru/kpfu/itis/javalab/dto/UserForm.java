package ru.kpfu.itis.javalab.dto;

import lombok.Data;
import ru.kpfu.itis.javalab.validation.ValidPassword;

import javax.validation.constraints.Email;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Data
public class UserForm {
    private String username;
    @Email
    private String email;
    @ValidPassword
    private String password;
}
