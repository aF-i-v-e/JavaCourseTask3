package ru.avelichko.NauJava.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.avelichko.NauJava.dao.AccountUserRepositoryCustom;
import ru.avelichko.NauJava.model.AccountUser;
import ru.avelichko.NauJava.repository.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("/custom/account_users")
public class AccountUserCustomController {
    @Autowired
    private AccountUserRepositoryCustom accountUserRepositoryCustom;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/findByLogin")
    public List<AccountUser> findByLogin(@RequestParam String login) {
        return accountUserRepositoryCustom.findByLogin(login);
    }

    @GetMapping("/findByRole")
    public List<AccountUser> findByRole(@RequestParam String role) {
        return accountUserRepositoryCustom.findByRole(role);
    }
}
