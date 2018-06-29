package creditLine.services.mvp_pv;

import java.util.List;


import creditLine.persistence.entities.Client;


public interface ClientService {

	void save();

	List<Client> findAll();	
	
	int deleteByIdclient(int idclient);

	void updateClient();

	void findByIdclient(int idclient);

	void searchByIdClient(int idClient);

	void addClient();

	void updateClientProcess();

	void refreshClient();

	void clearTClientFields();

	void displayClientSelected();




	
	
}
