package cristoffer85.exam.flakevilleserver.BUDGET.service;

import cristoffer85.exam.flakevilleserver.BUDGET.model.Budget;
import cristoffer85.exam.flakevilleserver.BUDGET.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget getBudgetByUsername(String username) {
        return budgetRepository.findByUsername(username).orElseGet(() -> {
            Budget newBudget = new Budget(username);
            return budgetRepository.save(newBudget);
        });
    }

    public Budget updateBudget(String username, String fieldName, Double value) {
        Budget budget = getBudgetByUsername(username);
        budget.getFields().put(fieldName, value);
        return budgetRepository.save(budget);
    }

    public Budget deleteField(String username, String fieldName) {
        Budget budget = getBudgetByUsername(username);
        budget.getFields().remove(fieldName);
        return budgetRepository.save(budget);
    }
}