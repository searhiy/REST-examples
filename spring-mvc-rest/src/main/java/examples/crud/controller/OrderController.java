package examples.crud.controller;

import examples.crud.domain.Order;
import examples.crud.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by serhii on 06.11.14.
 */
@Controller
@RequestMapping("/crud")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderServiceIml;

    @RequestMapping(method = RequestMethod.GET, value = "/orders/{orderNumber}", produces = "application/json")
    public
    @ResponseBody
    Order getOrder(@PathVariable("orderNumber") String orderNumber) {
        //MediaType.APPLICATION_JSON_VALUE

        logger.info("getting user by order number={} from database", orderNumber);
        return orderServiceIml.findByOrderNumber(orderNumber);
    }
}
