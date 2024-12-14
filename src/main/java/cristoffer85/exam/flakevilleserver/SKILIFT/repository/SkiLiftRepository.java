package cristoffer85.exam.flakevilleserver.SKILIFT.repository;

import cristoffer85.exam.flakevilleserver.SKILIFT.model.SkiLift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiLiftRepository extends MongoRepository<SkiLift, Long> {
}
