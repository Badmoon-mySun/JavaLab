package ru.kpfu.itis.javalab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.javalab.services.SignUpService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Controller
@RequestMapping("/confirm/*")
public class ConfirmController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public String confirm(HttpServletRequest request) {
        String code = request.getRequestURI().replace("/confirm/", "");
        signUpService.confirm(code);
        return "redirect:/home";
    }
}
