package cristoffer85.exam.snofjallbywithptbackend.SKILIFT.repository;

import cristoffer85.exam.snofjallbywithptbackend.SKILIFT.model.SkiLift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiLiftRepository extends MongoRepository<SkiLift, Long> {
}
