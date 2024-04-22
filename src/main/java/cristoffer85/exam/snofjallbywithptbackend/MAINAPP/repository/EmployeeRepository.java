package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findByUsername(String username);
}
