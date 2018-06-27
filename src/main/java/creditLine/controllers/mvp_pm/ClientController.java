package creditLine.controllers.mvp_pm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import creditLine.persistence.daos.ClientRepository;
import creditLine.persistence.entities.Account;
import creditLine.persistence.entities.Client;
import creditLine.services.mvp_pm.ClientService;


@Service
@Transactional
@Lazy
public class ClientController implements ClientService {

	private ClientRepository repository;

	private int idClient;
	private String name;
	private String surname;
	private String address;
	private String nationality;

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
		Client client = new Client(getName(), getSurname(), getAddress(), getNationality());
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
		repository.updateClient(getIdClient(), getName(), getSurname(), getAddress(), getNationality());
	}

	@Override
	public int deleteByIdclient(int idclient) {
		return repository.deleteByIdclient(idclient);
	}


	@Override
	public boolean findByIdclient(int idclient) {
		Client client = repository.findByIdclient(idclient);
		if (!StringUtils.isEmpty(client)){
			setName(client.getName()); 
			setSurname(client.getSurname());
			setAddress(client.getAddress());
			setNationality(client.getNationality());
			setIdClient(client.getIdclient());
			return true;
		}
		return false;
	}

	@Override
	public int getIdClient() {
		return idClient;
	}

	@Override
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	@Override
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getNationality() {
		return nationality;
	}

	@Override
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
