package examples.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by serhii on 10.11.14.
 */
@ContextConfiguration(locations = {"classpath:META-INF/boot/applicationContext.xml",
        "classpath:examples/service/test-mvc-servlet.xml"}, loader = ContextLoader.class)
public class OrderControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext applicationContext;

    //@Test
    public void testGettingOrder() {
        //applicationContext.getBean("order_service");

    }
}
