package ru.avelichko.NauJava.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.avelichko.NauJava.model.Account;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Account> accountContainer() {
        return new ArrayList<>();
    }
}
