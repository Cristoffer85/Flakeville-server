package cristoffer85.exam.snofjallbywithptbackend.repository;

import cristoffer85.exam.snofjallbywithptbackend.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByUsername(String username);
    void deleteByUsername(String username);
}
