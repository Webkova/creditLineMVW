package creditLine.services.mvp_pv;

import java.util.List;

import creditLine.persistence.entities.Client;

public interface ClientService {

	Client save(Client client);

	List<Client> findAll();	
	
	int deleteByIdclient(int idclient);
	
	void deleteClient();

	void updateClient(int idclient, String name, String surname, String address, String nationality);
	
	void updateClient();

	void updateClientTable();
	
	void showClientSelected();
	
	void addClient();
	
	
}
