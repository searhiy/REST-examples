package examples.repository;

import examples.crud.domain.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by serhii on 06.11.14.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    public Order findById(int id);

    public List<Order> findByClientId(int clientId, Pageable pageable);
}
