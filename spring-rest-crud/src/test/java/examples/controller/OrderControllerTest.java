package examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by serhii on 10.11.14.
 */
@ContextConfiguration(locations = {"classpath:META-INF/boot/applicationContext.xml",
        "classpath:examples/crud/test-mvc-servlet.xml"}, loader = ContextLoader.class)
@WebAppConfiguration
public class OrderControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeMethod
    public void initMockMvc() {
        this.mockMvc = webAppContextSetup(wac).build();
    }

    public final static int ORDER_NUMBER = 1;

    @Test
    public void testGettingOrder() throws Exception {
        mockMvc.perform(get("/orders/" + ORDER_NUMBER).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.date", is(1400965200000L)));
    }
}
