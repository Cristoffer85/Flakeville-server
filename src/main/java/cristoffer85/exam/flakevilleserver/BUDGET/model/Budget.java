package cristoffer85.exam.flakevilleserver.BUDGET.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "budgets")
public class Budget {

    @Id
    private String id;

    private String username;

    private Map<String, Double> fields = new HashMap<>(); // Custom fields with names and values

    public Budget() {
        super();
    }

    public Budget(String username) {
        this.username = username;
    }
}