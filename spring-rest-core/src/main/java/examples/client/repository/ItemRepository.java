package examples.client.repository;

import examples.client.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by serhii on 19.11.14.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
