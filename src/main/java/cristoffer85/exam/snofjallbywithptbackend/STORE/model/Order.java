package cristoffer85.exam.snofjallbywithptbackend.STORE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    private List<Product> products;
}
