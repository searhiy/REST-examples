package examples.hateoas.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.testng.Assert;

import java.net.URI;

/**
 * Created by serhii on 11.11.14.
 */
public class GreetingTest {

    //@Test
    public void testGettingOrder() throws Exception {
        Traverson traverson = new Traverson(new URI("http://localhost:8081/spring-mvc-rest/hateoas/greeting"), MediaTypes.HAL_JSON);
        String greeting = traverson.follow().toObject("$.content");
        Assert.assertEquals(greeting, "Hello, World!");
    }
}
