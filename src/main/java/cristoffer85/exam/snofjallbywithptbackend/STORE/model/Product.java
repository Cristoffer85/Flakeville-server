package cristoffer85.exam.snofjallbywithptbackend.STORE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    public static final String SEQUENCE_NAME = "products_sequence";

    @Id
    private long id;
    private String name;
    private String description;
    private double price;
}