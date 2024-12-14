package cristoffer85.exam.flakevilleserver.STORE.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// I wanted quantity to add in same order history, the JSON-response couldnt map it so had to do this way (Error 415 unsupported media-type otherwise)

@Data
@AllArgsConstructor
public class ProductQuantity {

    private Product product;
    private Integer quantity;

}
