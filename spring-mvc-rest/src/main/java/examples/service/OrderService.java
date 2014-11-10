package examples.service;

import examples.domain.Order;

/**
 * Created by serhii on 06.11.14.
 */
public interface OrderService {

    public Order findByOrderNumber(String oderNumber);
}
