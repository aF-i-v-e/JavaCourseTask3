package ru.avelichko.NauJava.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Категория доходов
 * WAGES("wages"),
 * SCHOLARSHIP("scholarship"),
 * OTHER("other");
 */
@Entity
@Table(name = "income_category")
public class IncomeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "income_category_id")
    private Long incomeCategoryId;

    @Column(name = "income_category_name")
    private String incomeCategoryName;

    @OneToMany(mappedBy = "incomeCategory")
    private List<Income> incomes;

    public IncomeCategory() {
    }

    public IncomeCategory(String incomeCategoryName) {
        this.incomeCategoryName = incomeCategoryName;
    }

    public Long getIncomeCategoryId() {
        return incomeCategoryId;
    }

    public void setIncomeCategoryId(Long incomeCategoryId) {
        this.incomeCategoryId = incomeCategoryId;
    }

    public String getIncomeCategoryName() {
        return incomeCategoryName;
    }

    public void setIncomeCategoryName(String incomeCategoryName) {
        this.incomeCategoryName = incomeCategoryName;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }
}
