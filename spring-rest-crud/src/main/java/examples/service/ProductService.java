package examples.service;

import examples.crud.domain.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by serhii on 17.11.14.
 */
public interface ProductService {

    public Product saveProduct(Product product);

    public Product findProduct(int id);

    public void deleteProduct(int id);

    public List<Product> list(int orderId, int page, int size);

    public List<Product> sortedList(int page, int size, Map<String, String> sort);

    public List<Product> search();
}
