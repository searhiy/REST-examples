package examples.client.service;

import examples.client.domain.Item;
import examples.client.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by serhii on 19.11.14.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Qualifier("itemRepository")
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item getItem(long id) {
        return itemRepository.getOne(id);
    }

    @Override
    public List<Item> findAll(Pageable pageable){
        return itemRepository.findAll(pageable).getContent();
    }

    public List<Item> save(Iterable<Item> items){
        return itemRepository.save(items);
    }

    @Override
    public Item save(Item item) {return itemRepository.save(item);}

    @Override
    public void delete(long id) {
        itemRepository.delete(id);
    }
}
