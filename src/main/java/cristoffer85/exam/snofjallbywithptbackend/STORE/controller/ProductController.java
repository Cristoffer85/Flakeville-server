package cristoffer85.exam.snofjallbywithptbackend.STORE.controller;

import cristoffer85.exam.snofjallbywithptbackend.STORE.model.Product;
import cristoffer85.exam.snofjallbywithptbackend.STORE.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getOneProduct/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/updateProduct/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
