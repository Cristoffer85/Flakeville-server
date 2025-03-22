package cristoffer85.exam.flakevilleserver.BUDGET.repository;

import cristoffer85.exam.flakevilleserver.BUDGET.model.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BudgetRepository extends MongoRepository<Budget, String> {
    Optional<Budget> findByUsername(String username);
}