package examples.crud.service;

import examples.crud.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;

/**
 * Created by serhii on 06.11.14.
 */
@ContextConfiguration(locations = {"classpath:META-INF/boot/applicationContext.xml",
        "classpath:examples/crud/test-mvc-servlet.xml"}, loader = ContextLoader.class)
public class OrderServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext applicationContext;

    public final static String ORDER_NUMBER = "N1E-12N-WK8-KS3";
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    public final static String EXPECTED_DATE = "20140525";

    @Test
    public void testGettingOrder() {
        OrderService orderService = (OrderService) applicationContext.getBean("order_service");
        Order order = orderService.findOrder(1);

        Assert.assertEquals(sdf.format(order.getDate()), EXPECTED_DATE, "wrong date");
    }
}
