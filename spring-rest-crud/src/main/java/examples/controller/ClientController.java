package examples.controller;

import examples.crud.domain.Client;
import examples.crud.domain.Order;
import examples.crud.domain.Product;
import examples.service.ClientService;
import examples.service.OrderService;
import examples.service.ProductService;
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
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody Client client) {
        logger.info("creating {}", client.toString());
        return clientService.saveClient(client);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<Client> listClients(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("getting orders");
        return clientService.list(page, size);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{client_id}")
    @ResponseStatus(HttpStatus.OK)
    public Client gettingClient(@PathVariable int client_id) {
        logger.info("reading client by id={}", client_id);
        return clientService.findClient(client_id);
    }

    /*
    with matrix variables
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{client_id}")
    @ResponseStatus(HttpStatus.OK)
    public Client gettingClient(@PathVariable int client_id,
                                @MatrixVariable Map<String, String> params) {
        logger.info("reading client by id={}", client_id);
        return clientService.findClient(client_id);
    }*/

    @RequestMapping(method = RequestMethod.GET, value = "/{client_id}/orders")
    public List<Order> listOrders(@PathVariable int client_id,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("getting orders");
        return orderService.list(client_id, page, size);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{client_id}/orders/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    public Order gettingOrder(@PathVariable int order_id) {
        logger.info("reading order by order_id={}", order_id);
        return orderService.findOrder(order_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{client_id}/orders/{order_id}/products")
    public List<Product> listProducts(@PathVariable int order_id,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("getting orders");
        return productService.list(order_id, page, size);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{client_id}/orders/{order_id}/products/{product_id}")
    @ResponseStatus(HttpStatus.OK)
    public Product gettingProduct(@PathVariable int product_id) {
        logger.info("reading order by order_id={}", product_id);
        return productService.findProduct(product_id);
    }

    /*@RequestMapping(
            method = RequestMethod.PUT,
            value = "/{id}")
    public Order replace(@PathVariable int id, @RequestBody Order order) {
        Order dbOrder = orderService.findClient(id);

        // here you can use some mapper (merge)
        dbOrder.setOrderNumber(order.getOrderNumber());
        dbOrder.setDate(order.getDate());

        logger.info("updating {}", dbOrder.toString());
        return orderService.saveClient(dbOrder);
    }*/

    /*@RequestMapping(
            method = RequestMethod.PATCH,
            value = "/{id}")
    public Order update(@PathVariable int id, @RequestBody Order order) {
        Order dbOrder = orderService.findClient(id);

        // here you can use validators or some addition logic (dto)
        if (order.getOrderNumber() != null) {
            dbOrder.setOrderNumber(order.getOrderNumber());
        }
        if (order.getDate() != null) {
            dbOrder.setDate(order.getDate());
        }

        logger.info("updating {}", order.toString());
        return orderService.saveClient(order);
    }*/

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{client_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable int client_id) {
        logger.info("deleting client by id={}", client_id);
        clientService.deleteClient(client_id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{client_id}/orders/{order_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable int order_id) {
        logger.info("deleting client by id={}", order_id);
        orderService.deleteOrder(order_id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{client_id}/orders/{order_id}/products/{product_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int product_id) {
        logger.info("deleting product by id={}", product_id);
        productService.deleteProduct(product_id);
    }

    // pageable list
    /*@RequestMapping(method = RequestMethod.GET, value = "")
    public List<Order> list(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("getting orders");
        return orderService.list(page, size);
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
        return orderService.sortedList(page, size, sort);
    }*/

    /*
    NOTE: you cannot use matrix variables with request params together, see example below
    @RequestMapping(
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Order> sortedList(@RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                  @RequestParam(required = false, value = "size", defaultValue = "10") int size,
                                  @MatrixVariable Map<String, String> sort) {
        logger.info("getting orders sorted by defined params");
        return orderService.sortedList(page, size, sort);
    }*/

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
        // MatrixVariable doesn't work with all web servers, try to enable it in your web server

        return "stocks";
      }
    */

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Client> searchClient() {
        return clientService.search();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{client_id}/orders/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Client> searchOrder(@PathVariable int client_id) {
        return clientService.search();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{client_id}/orders/{order_id}/products/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Client> searchProduct(@PathVariable int client_id,
                                      @PathVariable int order_id) {
        return clientService.search();
    }
}
