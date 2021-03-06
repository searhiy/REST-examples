package examples.dto.service;

import examples.dto.dao.LocationDAO;
import examples.dto.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationDAO locationDao;
                          
    public Location get(final long id) {
        return locationDao.findOne(id);
    }

    public List<Location> list(int page, int size) {
        return locationDao.findAll(new PageRequest(page, size)).getContent();
    }

    public Location store(Location location) {
        return locationDao.save(location);
    }

    public void delete(Location ticket) {
        if (ticket.getId() != null) {
            locationDao.delete(ticket);
        } else {
            locationDao.delete(ticket);
        }
    }

}
