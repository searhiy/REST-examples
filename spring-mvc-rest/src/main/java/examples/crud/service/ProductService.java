package examples.crud.service;

import examples.crud.domain.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by serhii on 17.11.14.
 */
public interface ProductService {

    public Product saveOrder(Product product);

    public Product findOrder(int id);

    public void deleteOrder(int id);

    public List<Product> list(int page, int size);

    public List<Product> sortedList(int page, int size, Map<String, String> sort);

    public List<Product> search();
}
