package creditLine.services.mvp_pm;

import java.util.List;

import creditLine.persistence.entities.Client;

public interface ClientService {

	Client save(Client client);

	List<Client> findAll();	
	
	int deleteByIdclient(int idclient);

	void updateClient(int idclient, String name, String surname, String address, String nationality);


}
