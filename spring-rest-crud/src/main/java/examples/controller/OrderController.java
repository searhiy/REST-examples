package examples.controller;

import examples.crud.domain.Order;
import examples.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by serhii on 06.11.14.
 */
@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
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
        return orderServiceIml.saveOrder(order);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order read(@PathVariable int id) {
        logger.info("reading order by id={}", id);
        return orderServiceIml.findOrder(id);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/{id}")
    public Order replace(@PathVariable int id, @RequestBody Order order) {
        Order dbOrder = orderServiceIml.findOrder(id);

        // here you can use some mapper (merge)
        dbOrder.setOrderNumber(order.getOrderNumber());
        dbOrder.setDate(order.getDate());

        logger.info("updating {}", dbOrder.toString());
        return orderServiceIml.saveOrder(dbOrder);
    }

    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/{id}")
    public Order update(@PathVariable int id, @RequestBody Order order) {
        Order dbOrder = orderServiceIml.findOrder(id);

        // here you can use validators or some addition logic (dto)
        if (order.getOrderNumber() != null) {
            dbOrder.setOrderNumber(order.getOrderNumber());
        }
        if (order.getDate() != null) {
            dbOrder.setDate(order.getDate());
        }

        logger.info("updating {}", order.toString());
        return orderServiceIml.saveOrder(order);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        logger.info("getting order by id={}", id);
        orderServiceIml.deleteOrder(id);
    }

    // pageable list
    /*@RequestMapping(method = RequestMethod.GET, value = "")
    public List<Order> list(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("getting orders");
        return orderServiceIml.list(page, size);
    }*/

    /*
      you can use JSON for pass sorting parameters
     */
    /*@RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> sortedList(@RequestParam(required=false, value = "page", defaultValue = "0") int page,
                            @RequestParam(required=false, value = "size", defaultValue = "10") int size,
                            @RequestBody Map<String, String> sort) {
        logger.info("getting orders sorted by defined params");
        return orderServiceIml.sortedList(page, size, sort);
    }*/

    @RequestMapping(
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Order> sortedList(@RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                  @RequestParam(required = false, value = "size", defaultValue = "10") int size,
                                  @MatrixVariable Map<String, String> sort) {
        logger.info("getting orders sorted by defined params");
        return orderServiceIml.sortedList(page, size, sort);
    }

    /*
      GET /owners/42;q=11;r=12/pets/21;q=22;s=23

      @RequestMapping(value = "/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
      public void findPet(
            @MatrixVariable Map<String, String> matrixVars,
            @MatrixVariable(pathVar="petId"") Map<String, String> petMatrixVars) {

        // matrixVars: ["q" : [11,22], "r" : 12, "s" : 23]
        // petMatrixVars: ["q" : 11, "s" : 23]

      }
    */

    /*
      GET /matrixvars/stocks;BT.A=276.70,+10.90,+3.91;AZN=236.00,+103.00,+3.29;SBRY=375.50,+7.60,+2.07/account;name=roger;number=105;location=stoke-on-trent,uk

      @RequestMapping(value = "/{stocks}/{account}", method = RequestMethod.GET)
      public String showPortfolioValuesWithAccountInfo(
            @MatrixVariable(pathVar = "stocks") Map<String, List<String>> stocks,
            @MatrixVariable(pathVar = "account") Map<String, List<String>> accounts) {

        // code here

        return "stocks";
      }
    */

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Order> search(@PathVariable int id) {
        logger.info("updating {}", id);
        return orderServiceIml.search();
    }
}
