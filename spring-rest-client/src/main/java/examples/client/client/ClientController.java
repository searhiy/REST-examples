package examples.client.client;

import examples.client.domain.Item;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

        // or you can use some wrapper if you expect lists with different types
        ResponseEntity<Item[]> result = restTemplate.exchange(PROVIDER_URL, HttpMethod.GET, entity, Item[].class);
        model.addAttribute("items", Arrays.asList(result.getBody()));
        return "itempage";
    }
}
