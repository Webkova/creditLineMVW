package creditLine.services.mvp_pm;

import java.util.List;


import creditLine.persistence.entities.Client;


public interface ClientService {

	void save();

	List<Client> findAll();	
	
	int deleteByIdclient(int idclient);

	void updateClient();
	

	boolean findByIdclient(int idclient);

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




	
	
}
