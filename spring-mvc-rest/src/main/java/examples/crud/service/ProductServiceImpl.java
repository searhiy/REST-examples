package examples.crud.service;

import com.google.common.collect.Lists;
import examples.crud.domain.Product;
import examples.crud.repository.ProductRepository;
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
    public Product saveOrder(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findOrder(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteOrder(int id) {
        Product product = productRepository.findById(id);
        productRepository.delete(product);
    }

    @Override
    public List<Product> list(int page, int size) {
        return Lists.newArrayList(productRepository.findAll(
                new PageRequest(
                        page,
                        size)).getContent());
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
