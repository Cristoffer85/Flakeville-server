package cristoffer85.exam.flakevilleserver.SKILIFT.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "skilifts")
public class SkiLift {

    @Id
    private Long id;
    private String name;
    private boolean isOperating;
    private List<SkiLiftOperations> operations;
}
