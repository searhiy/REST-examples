package examples.controller;

import com.sun.syndication.feed.atom.Feed;
import examples.messageconv.JaxbList;
import examples.messageconv.domain.Organization;
import examples.messageconv.repo.OrganizationRepository;
import examples.util.AtomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by serhii on 25.11.14.
 */
@Controller
//@RestController
@RequestMapping(value = "/organizations")
public class OrganizationController {

    @Autowired
    private Jaxb2Marshaller jaxb2Mashaller;

    @Autowired
    private OrganizationRepository organizationRepository;

    @RequestMapping(method = RequestMethod.GET, headers="Accept=application/xml, application/json")
    @ResponseBody
    public JaxbList list(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        return new JaxbList(organizationRepository.findAll(new PageRequest(page, size)).getContent());
    }

    @RequestMapping(method=RequestMethod.GET, headers="Accept=application/atom+xml")
    public @ResponseBody
    Feed getEmpFeed() {
        List<Organization> organizations = organizationRepository.findAll();
        return AtomUtil.employeeFeed(organizations, jaxb2Mashaller);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers="Accept=application/xml, application/json")
    @ResponseBody
    public Organization read(@PathVariable long id) {
        return organizationRepository.findOne(id);
    }


}
