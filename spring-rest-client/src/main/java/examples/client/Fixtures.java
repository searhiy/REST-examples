package examples.client;

import examples.client.domain.Item;
import examples.client.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Fixtures {

    @Autowired
    private ItemServiceImpl itemService;

    @PostConstruct
    public void init() {
        List<Item> items = new ArrayList<>();
        items.add(createItem("item1", "param11", "param21"));
        items.add(createItem("item2", "param12", "param22"));
        items.add(createItem("item3", "param13", "param23"));
        items.add(createItem("item4", "param14", "param24"));
        items.add(createItem("item5", "param15", "param25"));

        itemService.save(items);
    }

    private Item createItem(String name, String param1, String param2) {
        Item item = new Item();
        item.setName(name);
        item.setParam1(param1);
        item.setParam2(param2);
        return item;
    }

}
