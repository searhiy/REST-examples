package examples.messageconv.repo;

import examples.messageconv.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by serhii on 25.11.14.
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
