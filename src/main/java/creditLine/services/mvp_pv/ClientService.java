package creditLine.services.mvp_pv;

import java.util.List;


import creditLine.persistence.entities.Client;


public interface ClientService {

	void save();

	List<Client> findAll();	
	
	int deleteByIdclient(int idclient);

	void updateClient();
	

	void findByIdclient(int idclient);

	int getIdClient();

	void setIdClient(int idClient);

	String getName();

	void setName(String name);

	String getSurname();

	void setSurname(String surname);

	String getAddress();

	void setAddress(String address);

	String getNationality();

	void setNationality(String nationality);

	void searchByIdClient(int idClient);

	void addClient();

	void updateClientProcess();




	
	
}
