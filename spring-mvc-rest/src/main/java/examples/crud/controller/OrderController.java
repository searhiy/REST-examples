package examples.crud.controller;

import examples.crud.domain.Order;
import examples.crud.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by serhii on 06.11.14.
 */
@RestController
@RequestMapping(value = "/crud/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderServiceIml;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order) {
        logger.info("creating {}", order.toString());
        return orderServiceIml.save(order);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{orderNumber}")
    public Order read(@PathVariable("orderNumber") String orderNumber) {
        logger.info("reading order by order number={}", orderNumber);
        return orderServiceIml.findByOrderNumber(orderNumber);
    }

    // POST is also possible
    @RequestMapping(method = RequestMethod.PATCH)
    public
    @ResponseBody
    Order update(@RequestBody Order order) {
        logger.info("updating {}", order.toString());
        return orderServiceIml.save(order);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{orderNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("orderNumber") String orderNumber) {
        logger.info("getting order by order number={}", orderNumber);
        orderServiceIml.deleteOrder(orderNumber);
    }

    // pageable list
    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<Order> list(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("getting all orders");
        return orderServiceIml.list(page, size);
    }
}
