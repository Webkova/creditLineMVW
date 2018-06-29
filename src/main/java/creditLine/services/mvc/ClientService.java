package creditLine.services.mvc;

import java.util.List;


import creditLine.persistence.entities.Client;


public interface ClientService {

	Client save(Client client);

	List<Client> findAll();	
	
	void deleteByIdclient(int idclient);

	void updateClient(int idclient, String name, String surname, String address, String nationality);
	
	
	void showClientSelected();
	
	void addClient(Client clientValidated);

	void findByIdclient(int idclient);


	
	
}
