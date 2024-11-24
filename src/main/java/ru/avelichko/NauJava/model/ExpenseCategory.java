package ru.avelichko.NauJava.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Категория расходов
 * FOOD("food"),
 * TRANSPORT("transport"),
 * HEALTH("health"),
 * HOUSING("housing"),
 * HOBBY("hobby"),
 * CLOTHING("clothing"),
 * ENTERTAINMENTS("entertainments"),
 * OTHER("other");
 */

@Entity
@Table(name = "expense_category")
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expense_category_id")
    private Long expenseCategoryId;

    @Column(name = "expense_category_name")
    private String expenseCategoryName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "expenseCategory")
    private List<Expense> expenses;

    public ExpenseCategory() {

    }

    public ExpenseCategory(String name) {
        this.expenseCategoryName = name;
    }

    public ExpenseCategory(String expenseCategoryName, List<Expense> expenses) {
        this.expenseCategoryName = expenseCategoryName;
        this.expenses = expenses;
    }

    public Long getExpenseCategoryId() {
        return expenseCategoryId;
    }

    public void setExpenseCategoryId(Long expenseCategoryId) {
        this.expenseCategoryId = expenseCategoryId;
    }

    public String getExpenseCategoryName() {
        return expenseCategoryName;
    }

    public void setExpenseCategoryName(String expenseCategoryName) {
        this.expenseCategoryName = expenseCategoryName;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}