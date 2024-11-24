package ru.avelichko.NauJava.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.avelichko.NauJava.service.AccountUserService;

@Controller
public class MainController {
    @Autowired
    private AccountUserService accountUserService;

    @RequestMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = accountUserService.getNameByLogin(auth.getName());
        model.addAttribute("name", name);
        return "index";
    }
}
