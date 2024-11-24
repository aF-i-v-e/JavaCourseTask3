package ru.avelichko.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.avelichko.NauJava.model.AccountUser;
import ru.avelichko.NauJava.model.Role;
import ru.avelichko.NauJava.repository.AccountUserRepository;
import ru.avelichko.NauJava.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final AccountUserRepository accountUserRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, AccountUserRepository accountUserRepository,
                           PlatformTransactionManager transactionManager) {
        this.roleRepository = roleRepository;
        this.accountUserRepository = accountUserRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Role> findByTitle(String roleTitle) {
        return roleRepository.findByTitle(roleTitle);
    }

    @Override
    public void deleteRole(String roleTitle) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            // удалить пользователей с данной ролью
            List<AccountUser> accountUsers = accountUserRepository.findByRole(roleTitle);
            for (AccountUser accountUser : accountUsers) {
                accountUserRepository.delete(accountUser);
            }
            // удалить роль
            roleRepository.deleteByTitle(roleTitle);
            // фиксация транзакции
            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            // откатить транзакцию в случае ошибки
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
