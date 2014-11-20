package examples.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String test(Model model) throws IOException {
        return "home";
    }
}
