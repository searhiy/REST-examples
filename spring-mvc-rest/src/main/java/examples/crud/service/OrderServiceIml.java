package examples.crud.service;

import examples.crud.domain.Order;
import examples.crud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by serhii on 06.11.14.
 */
@Service("order_service")
@Repository
@Transactional
public class OrderServiceIml implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }
}
