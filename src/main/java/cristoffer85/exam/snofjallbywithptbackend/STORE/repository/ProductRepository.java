package cristoffer85.exam.snofjallbywithptbackend.STORE.repository;

import cristoffer85.exam.snofjallbywithptbackend.STORE.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
