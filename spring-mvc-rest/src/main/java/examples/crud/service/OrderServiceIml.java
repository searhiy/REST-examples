package examples.crud.service;

import com.google.common.collect.Lists;
import examples.crud.domain.Order;
import examples.crud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public void deleteOrder(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        orderRepository.delete(order);
    }

    @Override
    public List<Order> list(int page, int size) {
        return Lists.newArrayList(orderRepository.findAll(new PageRequest(page, size)).getContent());
    }


}
