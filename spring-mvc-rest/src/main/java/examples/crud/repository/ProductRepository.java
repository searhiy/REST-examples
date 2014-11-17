package examples.crud.repository;

import examples.crud.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by serhii on 17.11.14.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product findById(int id);
}
