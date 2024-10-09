package ru.avelichko.NauJava.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.avelichko.NauJava.model.AccountUser;
import ru.avelichko.NauJava.model.Role;

import java.util.List;

@Repository
public class AccountUserRepositoryImpl implements AccountUserRepositoryCustom {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public AccountUserRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Long createAccountUser(String name, String surname, String login, String password, Role role) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        AccountUser accountUser = new AccountUser();
        accountUser.setName(name);
        accountUser.setSurname(surname);
        accountUser.setLogin(login);
        accountUser.setPassword(password);
        accountUser.setRole(role);
        entityManager.persist(accountUser);
        transaction.commit();
        return accountUser.getAccountUserId();
    }

    @Override
    public void deleteAccountUser(Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<AccountUser> delete = criteriaBuilder.createCriteriaDelete(AccountUser.class);
        Root<AccountUser> root = delete.from(AccountUser.class);
        delete.where(criteriaBuilder.equal(root.get("accountUserId"), userId));
        entityManager.createQuery(delete).executeUpdate();
        transaction.commit();
    }

    @Override
    public List<AccountUser> findByLogin(String login) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountUser> criteriaQuery = criteriaBuilder.createQuery(AccountUser.class);
        Root<AccountUser> accountUserRoot = criteriaQuery.from(AccountUser.class);
        Predicate namePredicate = criteriaBuilder.equal(accountUserRoot.get("login"), login);
        criteriaQuery.select(accountUserRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<AccountUser> findByRole(String roleTitle) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountUser> criteriaQuery = criteriaBuilder.createQuery(AccountUser.class);
        Root<AccountUser> accountUserRoot = criteriaQuery.from(AccountUser.class);
        Join<AccountUser, Role> role = accountUserRoot.join("role", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(role.get("title"), roleTitle);
        criteriaQuery.select(accountUserRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
