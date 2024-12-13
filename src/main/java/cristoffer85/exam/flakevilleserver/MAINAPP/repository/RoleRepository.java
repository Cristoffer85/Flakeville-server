package cristoffer85.exam.flakevilleserver.MAINAPP.repository;

import cristoffer85.exam.flakevilleserver.MAINAPP.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByAuthority(String authority);
}
