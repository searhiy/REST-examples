package examples.client.provider;

import examples.client.domain.Item;
import examples.client.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by serhii on 19.11.14.
 */
@RestController
@RequestMapping(value = "/provider/items")
public class ProviderController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Item> list(@RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size", defaultValue = "10") int size) {

        return itemService.findAll(new PageRequest(page, size));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Item getItem(@PathVariable int id) {
        return itemService.getItem(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Item create(@RequestBody Item item) {
        return itemService.save(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Item update(@PathVariable int id, @RequestBody Item item) {
        // see dto/versions project
        return itemService.save(item);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        itemService.delete(id);
    }
}
