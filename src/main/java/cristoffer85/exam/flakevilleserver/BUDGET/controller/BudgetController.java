package cristoffer85.exam.flakevilleserver.BUDGET.controller;

import cristoffer85.exam.flakevilleserver.BUDGET.model.Budget;
import cristoffer85.exam.flakevilleserver.BUDGET.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/{username}")
    public Budget getBudget(@PathVariable String username) {
        return budgetService.getBudgetByUsername(username);
    }

    @PostMapping("/{username}/addField")
    public Budget addField(@PathVariable String username, @RequestParam String fieldName, @RequestParam Double value) {
        return budgetService.updateBudget(username, fieldName, value);
    }

    @DeleteMapping("/{username}/deleteField")
    public Budget deleteField(@PathVariable String username, @RequestParam String fieldName) {
        return budgetService.deleteField(username, fieldName);
    }
}