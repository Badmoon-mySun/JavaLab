package ru.kpfu.itis.javalab.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }
}
