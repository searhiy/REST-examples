package examples.repository;

import examples.crud.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by serhii on 17.11.14.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client findById(int id);
}
