package examples.service;

import com.google.common.collect.Lists;
import examples.crud.domain.Product;
import examples.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by serhii on 17.11.14.
 */
@Service("product_service")
@Repository
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProduct(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id);
        productRepository.delete(product);
    }

    @Override
    public List<Product> list(int orderId, int page, int size) {
        return Lists.newArrayList(productRepository.findByOrderId(
                orderId,
                new PageRequest(
                        page,
                        size)));
    }

    @Override
    public List<Product> sortedList(int page, int size, Map<String, String> sort) {
        return null;
    }

    @Override
    public List<Product> search() {
        return null;
    }
}
