package ru.kpfu.itis.javalab.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.javalab.dto.UserForm;
import ru.kpfu.itis.javalab.services.SignUpService;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public String getSignUp() {
        return "signUp";
    }

    @PostMapping
    public String signUpUser(UserForm userForm) {
        signUpService.signUp(userForm);

        return "redirect:/home";
    }

}
