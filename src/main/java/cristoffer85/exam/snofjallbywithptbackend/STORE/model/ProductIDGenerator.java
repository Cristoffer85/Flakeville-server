package cristoffer85.exam.snofjallbywithptbackend.STORE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
   Class that handles generating a unique ID for each product,
   since mongoDB does not have a built in auto increment feature.
   This way makes it easier to remember the different ID:s.
*/

@Data
@AllArgsConstructor
@Document(collection = "ProductIDGenerator")
public class ProductIDGenerator {

    @Id
    private String id;
    private long seq;
}
