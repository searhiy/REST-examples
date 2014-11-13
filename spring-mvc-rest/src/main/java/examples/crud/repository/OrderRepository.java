package examples.crud.repository;

import examples.crud.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by serhii on 06.11.14.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    public Order findById(int id);
}
