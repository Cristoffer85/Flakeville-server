package cristoffer85.exam.snofjallbywithptbackend.repository;

import cristoffer85.exam.snofjallbywithptbackend.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
