package examples.crud.service;

import examples.crud.domain.Order;

import java.util.List;

/**
 * Created by serhii on 06.11.14.
 */
public interface OrderService {

    public Order save(Order order);

    public Order findByOrderNumber(String oderNumber);

    public void deleteOrder(String orderNumber);

    public List<Order> list(int page, int size);
}
