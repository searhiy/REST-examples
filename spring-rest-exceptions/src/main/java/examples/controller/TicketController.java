package examples.controller;

import examples.exceptions.domain.TicketEx;
import examples.service.TicketExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by serhii on 21.11.14.
 */
@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

    @Autowired
    private TicketExService ticketService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<TicketEx> list(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        return ticketService.list(page, size);
    }

    @RequestMapping(value = "/{ticketId}", method = RequestMethod.GET)
    public TicketEx read(@PathVariable long ticketId) {
        return ticketService.get(ticketId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TicketEx create(@Valid @RequestBody TicketEx ticket) {
        return ticketService.store(ticket);
    }

    @RequestMapping(value = "/{ticketId}", method = RequestMethod.PATCH)
    public TicketEx update(@PathVariable long ticketId, @Valid @RequestBody TicketEx ticket) {
        TicketEx previouslyPersisted = ticketService.get(ticketId);
        // map
        return ticketService.store(previouslyPersisted);
    }

    @RequestMapping(value = "/{ticketId}", method = RequestMethod.PUT)
    public TicketEx replace(@PathVariable long ticketId, @Valid @RequestBody TicketEx ticket) {
        TicketEx previouslyPersisted = ticketService.get(ticketId);
        ticket.setId(previouslyPersisted.getId());
        return ticketService.store(ticket);
    }

    @RequestMapping(value = "/{ticketId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable long ticketId) {
        ticketService.delete(ticketId);
    }
}
