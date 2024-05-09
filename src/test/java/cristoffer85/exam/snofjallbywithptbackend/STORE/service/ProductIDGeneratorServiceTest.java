package cristoffer85.exam.snofjallbywithptbackend.STORE.service;

import cristoffer85.exam.snofjallbywithptbackend.STORE.model.ProductIDGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductIDGeneratorServiceTest {

    @InjectMocks
    ProductIDGeneratorService productIDGeneratorService;

    @Mock
    MongoOperations mongoOperations;

    @Test
    void generateSequence() {
        String seqName = "testSeq";
        ProductIDGenerator productIDGenerator = new ProductIDGenerator("testSeq", 1);

        when(mongoOperations.findAndModify(any(Query.class), any(Update.class), any(), eq(ProductIDGenerator.class)))
                .thenReturn(productIDGenerator);

        long result = productIDGeneratorService.generateSequence(seqName);

        assertEquals(1, result);
    }
}