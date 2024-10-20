package ru.avelichko.NauJava.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "role")
    private List<AccountUser> accountUsers;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AccountUser> getAccountUsers() {
        return accountUsers;
    }

    public void setAccountUsers(List<AccountUser> accountUsers) {
        this.accountUsers = accountUsers;
    }
}

