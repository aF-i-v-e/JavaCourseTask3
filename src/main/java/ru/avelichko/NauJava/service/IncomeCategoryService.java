package ru.avelichko.NauJava.service;

import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.exception.ServiceException;
import ru.avelichko.NauJava.model.IncomeCategory;
import ru.avelichko.NauJava.repository.IncomeCategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class IncomeCategoryService {
    private final IncomeCategoryRepository incomeCategoryRepository;

    public IncomeCategoryService(IncomeCategoryRepository incomeCategoryRepository) {
        this.incomeCategoryRepository = incomeCategoryRepository;
    }

    public ArrayList<IncomeCategory> getIncomeCategories() {
        ArrayList<IncomeCategory> incomeCategories = new ArrayList<>();
        incomeCategoryRepository.findAll().forEach(incomeCategories::add);
        return incomeCategories;
    }

    public void deleteIncomeCategoryById(Long id) {
        incomeCategoryRepository.deleteById(id);
    }

    public void addIncomeCategory(IncomeCategory incomeCategory) throws ServiceException {
        List<IncomeCategory> incomeCategories = incomeCategoryRepository
                .findByIncomeCategoryName(incomeCategory.getIncomeCategoryName());
        if (incomeCategories.isEmpty()) {
            incomeCategoryRepository.save(incomeCategory);
        } else {
            throw new ServiceException(String.format(
                    "Expense category with name %s already exists!",
                    incomeCategory.getIncomeCategoryName()
            ));
        }
    }
}
