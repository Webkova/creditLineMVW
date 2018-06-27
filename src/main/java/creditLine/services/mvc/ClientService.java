package creditLine.services.mvc;

import java.util.List;


import creditLine.persistence.entities.Client;


public interface ClientService {

	Client save(Client client);

	List<Client> findAll();	
	
	int deleteByIdclient(int idclient);
	
	void deleteClient();

	void updateClient(int idclient, String name, String surname, String address, String nationality);
	
	
	void showClientSelected();
	
	void addClient();

	Client findByIdclient(int idclient);


	
	
}
