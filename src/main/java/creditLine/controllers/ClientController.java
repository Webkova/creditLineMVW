package creditLine.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import creditLine.persistence.daos.ClientRepository;
import creditLine.persistence.entities.Client;
import creditLine.services.ClientService;

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
		save(new Client("Andrés", "Devia", "Gran Vía, 5", "Española"));
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
	public void updateClient(int idclient, String name) {
		repository.updateClient(idclient, name);
	}
	
	@Override
	public int deleteByIdclient(int idclient) {
		return repository.deleteByIdclient(idclient);
	}


	

}
