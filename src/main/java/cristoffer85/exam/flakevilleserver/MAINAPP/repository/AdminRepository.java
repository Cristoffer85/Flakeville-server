package cristoffer85.exam.snofjallbywithptbackend.MAINAPP.repository;

import cristoffer85.exam.snofjallbywithptbackend.MAINAPP.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByUsername(String admin);
}
