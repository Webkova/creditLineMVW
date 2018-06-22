package creditLine.services;

import java.util.List;

import creditLine.persistence.entities.Client;

public interface ClientService {

	Client save(Client client);

	List<Client> findAll();
	
	void updateClient(int idclient, String name);	
	
	int deleteByIdclient(int idclient);
}
