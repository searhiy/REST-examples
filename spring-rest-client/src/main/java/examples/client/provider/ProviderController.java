package examples.client.provider;

import examples.client.domain.Item;
import examples.client.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by serhii on 19.11.14.
 */
@RestController
@RequestMapping(value = "/provider/items", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Item update(@PathVariable int id, @RequestBody Item item) {
        Item dbItem = itemService.getItem(id);
        // see dto pattern for modification
        if (item!=null && item.getName()!=null){
            dbItem.setName(item.getName());
        }
        if (item!=null && item.getParam1()!=null){
            dbItem.setParam1(item.getParam1());
        }
        if (item!=null && item.getParam2()!=null){
            dbItem.setParam2(item.getParam2());
        }
        return itemService.save(dbItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        itemService.delete(id);
    }
}
