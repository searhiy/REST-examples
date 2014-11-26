package examples.controller;

import examples.messageconv.domain.Organization;
import examples.messageconv.repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by serhii on 25.11.14.
 */
@RestController
@RequestMapping(value = "/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Organization> list(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        return organizationRepository.findAll(new PageRequest(page, size)).getContent();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Organization read(@PathVariable long id) {
        return organizationRepository.findOne(id);
    }

}
