package examples.service;

import examples.crud.domain.Client;

import java.util.List;
import java.util.Map;

/**
 * Created by serhii on 17.11.14.
 */
public interface ClientService {

    public Client saveClient(Client client);

    public Client findClient(int id);

    public void deleteClient(int id);

    public List<Client> list(int page, int size);

    public List<Client> sortedList(int page, int size, Map<String, String> sort);

    public List<Client> search();
}
