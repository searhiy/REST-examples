package examples.crud.service;

import com.google.common.collect.Lists;
import examples.crud.domain.Order;
import examples.crud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrder(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrder(int id) {
        Order order = orderRepository.findById(id);
        orderRepository.delete(order);
    }

    @Override
    public List<Order> list(int page, int size) {
        return Lists.newArrayList(orderRepository.findAll(
                new PageRequest(
                        page,
                        size)).getContent());
    }

    @Override
    public List<Order> sortedList(int page, int size, Map<String, String> sort) {
        PageRequest pageRequest;
        if (sort == null || sort.isEmpty()) {
            pageRequest = new PageRequest(page, size);
        } else {
            List<Sort.Order> orders = new ArrayList<>();
            Set<String> sortParams = sort.keySet();
            for (String sortParam : sortParams) {
                orders.add(new Sort.Order(Sort.Direction.fromString(sort.get(sortParam)), sortParam));
            }
            pageRequest = new PageRequest(
                    page,
                    size,
                    new Sort(orders));
        }
        return Lists.newArrayList(orderRepository.findAll(pageRequest).getContent());
    }

    /**
     * use JpaSpecificationExecutor in repository:
     *
     * @link http://docs.spring.io/spring-data/jpa/docs/1.7.1.RELEASE/reference/html/#specifications
     * @link http://stackoverflow.com/questions/20280708/filtering-database-rows-with-spring-data-jpa-and-spring-mvc
     * custom implementation: https://github.com/tkaczmarzyk/specification-arg-resolver-example/blob/master/src/main/java/net/kaczmarzyk/example/web/CustomerController.java
     */
    @Override
    public List<Order> search() {
        return null;
    }
}
