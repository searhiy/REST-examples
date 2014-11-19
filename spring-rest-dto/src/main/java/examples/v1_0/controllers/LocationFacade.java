package examples.v1_0.controllers;

import examples.Versions;
import examples.dto.domain.Location;
import examples.dto.domain.LocationScan;
import examples.dto.domain.Ticket;
import examples.dto.service.LocationService;
import examples.dto.service.TicketService;
import examples.service.MappingService;
import examples.v1_0.data.LocationScanVO;
import examples.v1_0.data.LocationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController("LocationFacadeV1")
@RequestMapping(produces = Versions.V1_0, consumes = Versions.V1_0)
public class LocationFacade {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private MappingService mappingService;

    @RequestMapping(value = "/location/{locationId}", method = RequestMethod.GET)
    public LocationVO getLocation(@PathVariable Long locationId) {
        Location location = locationService.get(locationId);
        return mappingService.map(location, LocationVO.class);
    }

    @RequestMapping(value = "/ticket/{ticketId}/locations", method = RequestMethod.GET)
    public List<LocationScanVO> getAllLocations(@PathVariable long ticketId) {
        Ticket ticket = ticketService.get(ticketId);
        List<LocationScan> locations = ticket.getLocationScans();
        return mappingService.map(locations, LocationScanVO.class);
    }

    @RequestMapping(value = "/ticket/{ticketId}/scan/{locationId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public LocationVO addLocationScan(@PathVariable long ticketId, @PathVariable long locationId) {
        Ticket ticket = ticketService.get(ticketId);
        Location location = ticketService.addLocationScan(ticket, locationId);
        return mappingService.map(location, LocationVO.class);
    }

    @RequestMapping(value = "/ticket/{ticketId}/scan/{locationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeLocationScan(@PathVariable long ticketId, @PathVariable long locationId) {
        Ticket ticket = ticketService.get(ticketId);
        ticketService.deleteLocationScan(ticket, locationId);
    }

}
