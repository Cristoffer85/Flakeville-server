package cristoffer85.exam.snofjallbywithptbackend.STORE.service;

import cristoffer85.exam.snofjallbywithptbackend.STORE.model.Product;
import cristoffer85.exam.snofjallbywithptbackend.STORE.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductIDGeneratorService productIDGeneratorService;

    @Test
    void getAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(products, result);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals(product, result);
        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    void getProductsByCategory() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productRepository.findAll((Example<Product>) any())).thenReturn(products);

        List<Product> result = productService.getProductsByCategory("testCategory");

        assertEquals(products, result);
        verify(productRepository, times(1)).findAll((Example<Product>) any());
    }

    @Test
    void createProduct() {
        Product product = new Product();
        product.setId(1L);

        when(productIDGeneratorService.generateSequence(Product.SEQUENCE_NAME)).thenReturn(1L);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.createProduct(new Product());

        assertEquals(product, result);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("testName");
        product.setPrice(100.0);
        product.setDescription("testDescription");
        product.setCategory("testCategory");

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.updateProduct(1L, product);

        assertEquals(product, result);
        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void deleteProduct() {
        doNothing().when(productRepository).deleteById(anyLong());

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(anyLong());
    }
}