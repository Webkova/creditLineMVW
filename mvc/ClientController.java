package creditLine.controllers.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import creditLine.listeners.HandleEvent;
import creditLine.persistence.daos.ClientRepository;
import creditLine.persistence.entities.Account;
import creditLine.persistence.entities.Client;
import creditLine.services.ClientService;
import creditLine.view.MainView;

@Service
@Transactional
@Lazy
public class ClientController implements ClientService {

	private ClientRepository repository;
	private MainView view;

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	private final String CREATION = "creation";
	private final String UPDATE = "update";
	private final String READ = "read";
	private final String DELETE = "delete";
	
	
	@Autowired
	public ClientController(MainView view, ClientRepository repository, ApplicationEventPublisher applicationEventPublisher) {
		this.repository = repository;
		this.view = view;
		this.eventPublisher = eventPublisher;
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
		Client client = new Client(name, surname, address, nationality);
		HandleEvent event = new HandleEvent(this, UPDATE, client, idclient);
		eventPublisher.publishEvent(event);
	}

	@Override
	public void deleteByIdclient(int idclient) {
		repository.deleteByIdclient(idclient);
		HandleEvent event = new HandleEvent(this, DELETE, idclient);
		eventPublisher.publishEvent(event);
	}

	@Override
	public void showClientSelected() {
		Client clientSelected = view.getClientSelected();
		view.displayClientSelected(clientSelected);
	}

	@Override
	public void addClient(Client clientValidated) {
		save(clientValidated);
		HandleEvent event = new HandleEvent(this, CREATION, clientValidated);
		eventPublisher.publishEvent(event);
	}


	@Override
	public void findByIdclient(int idclient) {
		Client client = repository.findByIdclient(idclient);
		HandleEvent event = new HandleEvent(this, READ, client);
		eventPublisher.publishEvent(event);
	}

}
