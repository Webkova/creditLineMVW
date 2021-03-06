package creditLine.controllers.mvp_sc;

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
import creditLine.services.mvp_sc.ClientService;
import creditLine.view.mvp_sc.MainView;

@Service
@Transactional
@Lazy
public class ClientController implements ClientService {

	private ClientRepository repository;
	private MainView view;
	
	
	@Autowired
	public ClientController(MainView view, ClientRepository repository) {
		this.repository = repository;
		this.view = view;
	}

	@PostConstruct
	public void generateTestData() {
		List<Account> account = new ArrayList<Account>();
		account.add(new Account("Cuenta de Ahorro", 300, 01));
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
	public void showClientSelected() {
		Client clientSelected =  view.getClientSelected();
		view.displayClientSelected(clientSelected);
	}

	@Override
	public void addClient() {
	
		if (view.validateClientData()) { 
			view.saveClient(view.createClient());
			view.clearTableColumns();
		}	
	}

	@Override
	public void deleteClient() {
		if (view.isDeleteClient()) {
			//view.updateTable();
		}
		
	}
	
	@Override
	public Client findByIdclient(int idclient) {
		Client client = repository.findByIdclient(idclient);
		return client;
	}
	
	




}
