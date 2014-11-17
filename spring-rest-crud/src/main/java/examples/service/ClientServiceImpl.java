package examples.service;

import com.google.common.collect.Lists;
import examples.crud.domain.Client;
import examples.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by serhii on 17.11.14.
 */
@Service("client_service")
@Repository
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findClient(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public void deleteClient(int id) {
        Client client = clientRepository.findById(id);
        clientRepository.delete(client);
    }

    @Override
    public List<Client> list(int page, int size) {
        return Lists.newArrayList(clientRepository.findAll(
                new PageRequest(
                        page,
                        size)).getContent());
    }

    @Override
    public List<Client> sortedList(int page, int size, Map<String, String> sort) {
        return null;
    }

    @Override
    public List<Client> search() {
        return null;
    }
}
