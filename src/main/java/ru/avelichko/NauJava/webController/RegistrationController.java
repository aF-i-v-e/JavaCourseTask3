package ru.avelichko.NauJava.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.avelichko.NauJava.exception.RegistrationException;
import ru.avelichko.NauJava.model.AccountUser;
import ru.avelichko.NauJava.service.AccountUserService;

@Controller
public class RegistrationController {

    @Autowired
    private AccountUserService accountUserService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addAccountUser(AccountUser accountUser, Model model) {
        try {
            accountUserService.addAccountUser(accountUser);
            return "redirect:/login";
        } catch (RegistrationException ex) {
            model.addAttribute("message", ex.getMessage());
            return "registration";
        }
    }
}
