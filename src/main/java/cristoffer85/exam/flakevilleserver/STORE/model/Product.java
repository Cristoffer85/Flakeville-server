package cristoffer85.exam.flakevilleserver.STORE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    // method that generates a unique id for each product and save into separate collection in mongoDB (there you can see all current unique ids = up to 10 digits = 9.999.999.999 products)
    public static final String SEQUENCE_NAME = "products_sequence";

    @Id
    private long id;
    private String name;
    private String description;
    private double price;
    private String category;
}