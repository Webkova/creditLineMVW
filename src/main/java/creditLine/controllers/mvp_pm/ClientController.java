package creditLine.controllers.mvp_pm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import creditLine.persistence.daos.ClientRepository;
import creditLine.persistence.entities.Account;
import creditLine.persistence.entities.Client;
import creditLine.services.mvp_pm.ClientService;

@Service
@Transactional
public class ClientController implements ClientService {

	private final ClientRepository repository;

	@Autowired
	public ClientController(ClientRepository repository) {
		this.repository = repository;
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



}
