package examples.dto.dao;

import examples.dto.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by serhii on 18.11.14.
 */
@Transactional
public interface LocationDAO extends JpaRepository<Location, Long> {
}
