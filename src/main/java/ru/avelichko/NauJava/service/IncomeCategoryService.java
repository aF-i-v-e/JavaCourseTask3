package ru.avelichko.NauJava.service;

import org.springframework.stereotype.Component;
import ru.avelichko.NauJava.domain.IncomeCategoryEnum;
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
        if (incomeCategories.isEmpty()) {
            addDefaultIncomeCategories(incomeCategories);
        }
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

    private void addDefaultIncomeCategories(List<IncomeCategory> incomeCategories) {
        List<IncomeCategoryEnum> incomeCategoryEnum = List.of(IncomeCategoryEnum.SALARY,
                IncomeCategoryEnum.SCHOLARSHIP, IncomeCategoryEnum.ADVANCE_PAYMENT,
                IncomeCategoryEnum.OTHER);
        for (IncomeCategoryEnum incomeCategoryType : incomeCategoryEnum) {
            IncomeCategory incomeCategory = new IncomeCategory(incomeCategoryType.getTitle());
            incomeCategoryRepository.save(incomeCategory);
            incomeCategories.add(incomeCategory);
        }
    }
}
