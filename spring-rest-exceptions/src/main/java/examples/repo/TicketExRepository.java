package examples.repo;

import examples.exceptions.domain.TicketEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by serhii on 21.11.14.
 */
@Repository
public interface TicketExRepository extends JpaRepository<TicketEx, Long> {

}
