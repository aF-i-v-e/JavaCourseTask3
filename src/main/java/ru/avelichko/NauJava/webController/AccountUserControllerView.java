package ru.avelichko.NauJava.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.avelichko.NauJava.model.AccountUser;
import ru.avelichko.NauJava.repository.AccountUserRepository;

@Controller
@RequestMapping("/custom/account_users/view")
public class AccountUserControllerView {
    @Autowired
    private AccountUserRepository accountUserRepository;

    @GetMapping("/list")
    public String userListView(Model model) {
        Iterable<AccountUser> products = accountUserRepository.findAll();
        model.addAttribute("accountUsers", products);
        return "accountUsers";
    }
}
