package examples.client.service;

import examples.client.domain.Item;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by serhii on 19.11.14.
 */
public interface ItemService {

    public Item getItem(long id);

    public List<Item> findAll(Pageable pageable);

    public Item save(Item item);

    public void delete(long id);

}
