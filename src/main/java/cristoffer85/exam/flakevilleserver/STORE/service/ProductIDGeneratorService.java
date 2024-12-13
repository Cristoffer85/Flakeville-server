package cristoffer85.exam.flakevilleserver.STORE.service;

import cristoffer85.exam.flakevilleserver.STORE.model.ProductIDGenerator;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

/*
   Class that handles gernerating a unique ID for each product,
   since mongoDB does not have a built in auto increment feature.
   This way makes it easier to remember the different ID:s.
*/

@Service
public class ProductIDGeneratorService {

    private final MongoOperations mongoOperations;

    public ProductIDGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seqName) {
        ProductIDGenerator counter = mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq",1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                ProductIDGenerator.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
