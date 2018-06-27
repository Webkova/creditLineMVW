package creditLine.services.mvvm;

import java.util.List;


import creditLine.persistence.entities.Client;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;


public interface ClientService {

	void save();

	List<Client> findAll();	
	
	int deleteByIdclient(int idclient);

	void updateClient();
	

	void findByIdclient(int idclient);

	StringProperty getNationalityBinder();

	StringProperty getAddressBinder();

	StringProperty getSurnameBinder();

	StringProperty getNameBinder();

	IntegerProperty getIdClient();

	void setIdClient(IntegerProperty idClient);




	
	
}
