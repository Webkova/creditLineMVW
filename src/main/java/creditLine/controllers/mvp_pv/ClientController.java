package creditLine.controllers.mvp_pv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


import creditLine.persistence.daos.ClientRepository;
import creditLine.persistence.entities.Account;
import creditLine.persistence.entities.Client;
import creditLine.services.mvp_pv.ClientService;
import creditLine.view.mvp_pv.MainView;

@Service
@Transactional
@Lazy
public class ClientController implements ClientService {
	
	private int idclient;
	private String name;
	private String surname;
	private String address;
	private String nationality;
	private String creationDate;

	private ClientRepository repository;
	private MainView view;
	
	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@Autowired
	public ClientController(MainView view, ClientRepository repository) {
		this.repository = repository;
		this.view = view;
	}

	@PostConstruct
	public void generateTestData() {
		List<Account> account = new ArrayList<Account>();
		account.add(new Account("Cuenta de Ahorro", 300, 01));
		account.add(new Account("Cuenta de Corriente", 302, 02));
		account.add(new Account("Cuenta de Corriente moneda extranjera", 304, 01));
		save(new Client("Andrés", "Devia", "Gran Vía, 5", "Española", account));
		save(new Client("Marcos", "Lema", "Colón, 9", "Española"));
	}

	@Override
	public Client save(Client client) {
		return repository.save(client);
	}

	@Override
	public List<Client> findAll() {
		return repository.findAll();
	}
	
	@Override
	public void updateClient(int idclient, String name, String surname, String address, String nationality) {
		repository.updateClient(idclient, name, surname, address, nationality);
	}
	
	@Override
	public int deleteByIdclient(int idclient) {
		return repository.deleteByIdclient(idclient);
	}

	@Override
	public void updateClientTable() {
		List<Client> clients = view.getAllClients();	
	//	view.updateClientTableView(clients); 
	}

	@Override
	public void showClientSelected() {
		Map clientSelected =  (Map) view.getClientSelected();
		view.displayClientSelected(clientSelected);
	}

	@Override
	public void addClient() {
				
		if (view.isDataTocreateClient()) {
			Client clientAdded = new Client(getName(), getSurname(), getAddress(), getNationality());
			repository.save(clientAdded);
			view.clearTableColumns();
		}	
	}

	@Override
	public void updateClient() {
		boolean isUpdated = view.isClientDataFilled();
		if (isUpdated) {
			view.refreshClient();
			view.updateTable();
		}
		
	}

	@Override
	public void deleteClient() {
		if (view.isDeleteClient()) {
			view.updateTable();
		}
		
	}
	
	




}
