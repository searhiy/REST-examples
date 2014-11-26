package examples.util;

import examples.messageconv.domain.Organization;
import examples.messageconv.repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class FixturesEx {

    @Autowired
    private OrganizationRepository organizationRepository;

    @PostConstruct
    public void init() {
        List<Organization> organizations = new ArrayList<>();
        Organization organization1 = new Organization();
        organization1.setName("Org1");
        organizations.add(organization1);
        Organization organization2 = new Organization();
        organization2.setName("Org2");
        organizations.add(organization2);
        Organization organization3 = new Organization();
        organization3.setName("Org3");
        organizations.add(organization3);
        Organization organization4 = new Organization();
        organization4.setName("Org4");
        organizations.add(organization4);
        Organization organization5 = new Organization();
        organization5.setName("Org5");
        organizations.add(organization5);
        organizationRepository.save(organizations);
    }

}
