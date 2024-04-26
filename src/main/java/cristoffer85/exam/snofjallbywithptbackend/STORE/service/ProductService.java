package cristoffer85.exam.snofjallbywithptbackend.STORE.service;

import cristoffer85.exam.snofjallbywithptbackend.STORE.model.Product;
import cristoffer85.exam.snofjallbywithptbackend.STORE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductIDGeneratorService productIDGeneratorService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        product.setId(productIDGeneratorService.generateSequence(Product.SEQUENCE_NAME));
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id " + id));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription()); // Add this line
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
