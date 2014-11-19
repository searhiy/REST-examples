package examples.v2_0.controllers;

import examples.Versions;
import examples.dto.domain.Location;
import examples.dto.domain.Ticket;
import examples.dto.service.TicketService;
import examples.service.MappingService;
import examples.v1_0.data.LocationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Transactional
@RestController("LocationFacadeV2")
@RequestMapping(produces = Versions.V2_0, consumes = Versions.V2_0)
public class LocationFacade {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MappingService mappingService;

    @RequestMapping(value = "/ticket/{ticketId}/scan/{locationId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public LocationVO addLocationScan(@PathVariable long ticketId, @PathVariable long locationId) {
        Ticket ticket = ticketService.get(ticketId);
        Location location = ticketService.addLocationScan(ticket, locationId);
        return mappingService.map(location, LocationVO.class);
    }

}
