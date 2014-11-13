package examples.crud.service;

import examples.crud.domain.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by serhii on 06.11.14.
 */
public interface OrderService {

    public Order saveOrder(Order order);

    public Order findOrder(int id);

    public void deleteOrder(int id);

    public List<Order> list(int page, int size);

    public List<Order> sortedList(int page, int size, Map<String, String> sort);

    public List<Order> search();
}
