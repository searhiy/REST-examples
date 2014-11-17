package examples.repository;

import examples.crud.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by serhii on 17.11.14.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product findById(int id);

    public List<Product> findByOrderId(int orderId, Pageable pageable);
}
