package examples.client.client;

import examples.client.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by serhii on 19.11.14.
 */
@Controller
@RequestMapping(value = "/items")
public class ClientController {

    public static final String PROVIDER_URL = "http://localhost:8081/spring-rest-client/provider/items";

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Item> entity = new HttpEntity<Item>(headers);

        try{
            // or you can use some wrapper if you expect lists with different types
            ResponseEntity<Item[]> result = restTemplate.exchange(PROVIDER_URL, HttpMethod.GET, entity, Item[].class);
            model.addAttribute("items", Arrays.asList(result.getBody()));
        } catch (Exception e) {
            logger.error("Cannot execute api provider {}", PROVIDER_URL,  e);
        }
        return "itemspage";
    }

    /**
     * Retrieves the JSP add page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddPage(Model model) {
        model.addAttribute("item", new Item());
        return "addpage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addItem(@ModelAttribute("item") Item item,
                          Model model) {

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Item> entity = new HttpEntity<Item>(item, headers);

        try {
            restTemplate.exchange(PROVIDER_URL, HttpMethod.POST, entity, Item.class);
        } catch (Exception e) {
            logger.error("Cannot execute api provider {}", PROVIDER_URL,  e);
        }
        return "resultpage";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getItem(@RequestParam("id") Long id, Model model) {

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Item> entity = new HttpEntity<Item>(headers);

        // Send the request as GET
        try {
            ResponseEntity<Item> result = restTemplate.exchange(PROVIDER_URL +"/{id}", HttpMethod.GET, entity, Item.class, id);
            model.addAttribute("item", result.getBody());
        } catch (Exception e) {
            logger.error("Cannot execute api provider {}", PROVIDER_URL,  e);
        }

        return "getpage";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String getUpdatePage(@RequestParam(value="id", required=true) Integer id,
                                Model model) {
        logger.debug("Received request to show edit page");

        // Retrieve existing Person and add to model
        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<Item> entity = new HttpEntity<Item>(headers);

        // Send the request as GET
        try {
            ResponseEntity<Item> result = restTemplate.exchange(PROVIDER_URL + "/{id}", HttpMethod.GET, entity, Item.class, id);
            model.addAttribute("itemAttribute", result.getBody());
        } catch (Exception e) {
            logger.error("Cannot execute api provider {}", PROVIDER_URL, e);
        }

        return "updatepage";
    }

    /**
     * Sends an update request to the REST provider
     * based on the information submitted from the JSP update page.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePerson(@ModelAttribute("itemAttribute") Item item,
                               @RequestParam(value="id",  required=true) Long id,
                               Model model) {
        logger.debug("Update existing person");

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        // Pass the new person and header
        HttpEntity<Item> entity = new HttpEntity<Item>(item, headers);

        // Send the request as PUT
        ResponseEntity<String> result = restTemplate.exchange(PROVIDER_URL + "/{id}", HttpMethod.PUT, entity, String.class, id);

        return "resultpage";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePerson(@RequestParam("id") Long id,
                               Model model) {
        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        try {
            // Send the request as DELETE
            ResponseEntity<String> result = restTemplate.exchange(PROVIDER_URL + "/{id}", HttpMethod.DELETE, entity, String.class, id);
        } catch (Exception e) {
            logger.error("Cannot execute api provider {}", PROVIDER_URL, e);
        }
        return "resultpage";
    }

}
