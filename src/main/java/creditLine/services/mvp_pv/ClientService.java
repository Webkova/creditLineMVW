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
	
	public int getIdclient();

	public void setIdclient(int idclient);

	public String getName();

	public void setName(String name);

	public String getSurname();

	public void setSurname(String surname);

	public String getAddress();

	public void setAddress(String address);

	public String getNationality();

	public void setNationality(String nationality);

	public String getCreationDate();

	public void setCreationDate(String creationDate);
	
	
}
