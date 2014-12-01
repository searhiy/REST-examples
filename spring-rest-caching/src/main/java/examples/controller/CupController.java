package examples.controller;

import examples.caching.domain.Cup;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serhii on 01.12.14.
 */
@RestController
public class CupController {

    @RequestMapping(value = "/api/cups", method = RequestMethod.GET)
    public List<Cup> getCups() {
        List<Cup> cups = new ArrayList<>();
        Cup cup1 = new Cup();
        cup1.setColor("blue");
        cup1.setSize("1");
        cups.add(cup1);
        return cups;
    }

}
