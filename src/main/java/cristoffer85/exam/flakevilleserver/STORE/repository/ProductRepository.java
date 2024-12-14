package cristoffer85.exam.flakevilleserver.STORE.repository;

import cristoffer85.exam.flakevilleserver.STORE.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.springframework.lang.NonNull;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
    @NonNull Optional<Product> findById(@NonNull Long id);
}
