package examples.versions.controller;

import examples.versions.domain.Address;
import examples.versions.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by serhii on 18.11.14.
 */
@ContextConfiguration(locations = {"classpath:META-INF/boot/applicationContext.xml",
        "classpath:examples/crud/test-mvc-servlet.xml"}, loader = ContextLoader.class)
@WebAppConfiguration
public class AddressControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private AddressService addressService;

    @BeforeMethod
    public void initMockMvc() {
        this.mockMvc = webAppContextSetup(wac).build();
    }

    @BeforeMethod
    public void prepareTestData() {
        Address address = new Address("12043", "Berlin");
        addressService.save(address);
    }

    /* ------------------------------------------------------------------- */

    @Test
    public void should_respond_with_address_json_on_v1_url() throws Exception {
        mockMvc.perform(get("/apiurl/v1/address").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonContent())
                .andExpect(jsonPath("$.address", is("12043 Berlin")));
    }

    @Test
    public void should_respond_with_address_json_on_v2_url() throws Exception {
        mockMvc.perform(get("/apiurl/v2/address").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonContent())
                .andExpect(jsonPath("$.zip", is("12043")))
                .andExpect(jsonPath("$.town", is("Berlin")));
    }

    @Test
    public void should_respond_with_404_on_v3_url() throws Exception {
        mockMvc.perform(get("/apiurl/v3/address").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_respond_with_created_on_v1_url() throws Exception {
        mockMvc.perform(post("/apiurl/v1/address").param("address", "12055 Berlin"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void should_respond_with_created_on_v2_url() throws Exception {
        mockMvc.perform(post("/apiurl/v2/address").param("zip", "12055").param("town", "Berlin"))
                .andExpect(status().isAccepted());
    }

    /* ------------------------------------------------------------------- */

    @Test
    public void should_respond_with_hello_json_with_v1_header() throws Exception {
        mockMvc.perform(get("/apiheader/address").accept(APPLICATION_JSON).header("X-API-Version", "v1"))
                .andExpect(status().isOk())
                .andExpect(jsonContent())
                .andExpect(jsonPath("$.address", is("12043 Berlin")));
    }

    @Test
    public void should_respond_with_hello_json_with_v2_header() throws Exception {
        mockMvc.perform(get("/apiheader/address").accept(APPLICATION_JSON).header("X-API-Version", "v2"))
                .andExpect(status().isOk())
                .andExpect(jsonContent())
                .andExpect(jsonPath("$.zip", is("12043")))
                .andExpect(jsonPath("$.town", is("Berlin")));
    }

    @Test
    public void should_respond_with_bad_request_with_v3_header() throws Exception {
        mockMvc.perform(get("/apiheader/address").accept(APPLICATION_JSON).header("X-API-Version", "v3"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_respond_with_bad_request_without_version_header() throws Exception {
        mockMvc.perform(get("/apiheader/address").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_respond_with_created_with_v1_header() throws Exception {
        mockMvc.perform(post("/apiheader/address")
                .header("X-API-Version", "v1")
                .param("address", "12055 Berlin"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void should_respond_with_created_with_v2_header() throws Exception {
        mockMvc.perform(
                post("/apiheader/address")
                        .header("X-API-Version", "v2")
                        .param("zip", "12055")
                        .param("town", "Berlin"))
                .andExpect(status().isAccepted());
    }

    /* ------------------------------------------------------------------- */

    @Test
    public void should_respond_with_hello_json_with_v1_accept() throws Exception {
        mockMvc.perform(get("/apiaccept/address").accept("application/vnd.company.app-v1+json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/vnd.company.app-v1+json"))
                .andExpect(jsonPath("$.address", is("12043 Berlin")));
    }

    @Test
    public void should_respond_with_hello_json_with_v2_accept() throws Exception {
        mockMvc.perform(get("/apiaccept/address").accept("application/vnd.company.app-v2+json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/vnd.company.app-v2+json"))
                .andExpect(jsonPath("$.zip", is("12043")))
                .andExpect(jsonPath("$.town", is("Berlin")));
    }

    @Test
    public void should_respond_with_not_acceptable_with_v3_accept() throws Exception {
        mockMvc.perform(get("/apiaccept/address").accept("application/vnd.company.app-v3+json"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void should_respond_with_created_with_v1_accept() throws Exception {
        mockMvc.perform(
                post("/apiaccept/address")
                        .accept("application/vnd.company.app-v1+json")
                        .param("address", "12055 Berlin"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void should_respond_with_created_with_v2_accept() throws Exception {
        mockMvc.perform(
                post("/apiaccept/address")
                        .accept("application/vnd.company.app-v2+json")
                        .param("zip", "12055")
                        .param("town", "Berlin"))
                .andExpect(status().isAccepted());
    }

    private ResultMatcher jsonContent() {
        return content().contentType(APPLICATION_JSON);
    }
}
