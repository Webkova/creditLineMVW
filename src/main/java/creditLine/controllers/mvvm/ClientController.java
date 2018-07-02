package creditLine.controllers.mvvm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


import creditLine.persistence.daos.ClientRepository;
import creditLine.persistence.entities.Account;
import creditLine.persistence.entities.Client;
import creditLine.services.mvvm.ClientService;
import creditLine.view.mvvm.MainView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Service
@Transactional
@Lazy
public class ClientController implements ClientService {

	private ClientRepository repository;

	
	private IntegerProperty idClient = new SimpleIntegerProperty();
	private StringProperty name = new SimpleStringProperty();
	private StringProperty surname = new SimpleStringProperty();
	private StringProperty address = new SimpleStringProperty();
	private StringProperty nationality = new SimpleStringProperty();
	
	
	@Autowired
	public ClientController(ClientRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	public void generateTestData() {
		List<Account> account = new ArrayList<Account>();
		account.add(new Account("Cuenta de Ahorro", 300, 01));
		save(new Client("Andrés", "Devia", "Gran Vía, 5", "Española", account));
		save(new Client("Marcos", "Lema", "Colón, 9", "Española"));
		
	}

	@Override
	public void save() {
		Client client = new Client(name.getValue(), surname.getValue(), address.getValue(), 
								nationality.getValue());
		repository.save(client);
	}
	
	public void save(Client client) {
		repository.save(client);
	}

	@Override
	public List<Client> findAll() {
		return repository.findAll();
	}
	
	@Override
	public void updateClient() {
		repository.updateClient(idClient.getValue(), name.getValue(), surname.getValue(), address.getValue(), 
								nationality.getValue());
	}
	
	@Override
	public int deleteByIdclient(int idclient) {
		return repository.deleteByIdclient(idclient);
	}
	
	@Override
	public void findByIdclient(int idclient) {
		Client client = repository.findByIdclient(idclient);
		name.setValue(client.getName()); 
		surname.setValue(client.getSurname());
		address.setValue(client.getAddress());
		nationality.setValue(client.getNationality());
		idClient.setValue(client.getIdclient());

	}
	
	@Override
	public StringProperty getNameBinder() {
		return this.name;
	}

	@Override
	public StringProperty getSurnameBinder() {
		return this.surname;
	}

	@Override
	public StringProperty getAddressBinder() {
		return this.address;
	}

	@Override
	public StringProperty getNationalityBinder() {
		return this.nationality;
	}

	@Override
	public IntegerProperty getIdClient() {
		return idClient;
	}

	@Override
	public void setIdClient(IntegerProperty idClient) {
		this.idClient = idClient;
	}


}
