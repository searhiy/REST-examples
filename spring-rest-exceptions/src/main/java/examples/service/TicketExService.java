package examples.service;

import examples.exceptions.domain.TicketEx;
import examples.repo.TicketExRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by serhii on 21.11.14.
 */
@Transactional
@Service
public class TicketExService {

    @Autowired
    private TicketExRepository ticketExRepository;

    public TicketEx get(final long id) {
        TicketEx ticket = ticketExRepository.findOne(id);
        if (ticket != null) {
            return ticket;
        }
        throw new ObjectRetrievalFailureException(TicketEx.class, id);
    }

    public List<TicketEx> list(int page, int size) {
        return ticketExRepository.findAll(new PageRequest(page, size)).getContent();
    }

    public TicketEx store(TicketEx ticket) {
        return ticketExRepository.save(ticket);
    }

    public void delete(long id) {
        ticketExRepository.delete(id);
    }

    public Iterable<TicketEx> save(Iterable<TicketEx> iterable){
        return ticketExRepository.save(iterable);
    }

}
