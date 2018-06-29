package creditLine.controllers.mvp_pv;

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
import creditLine.services.mvp_pv.ClientService;
import creditLine.view.mvp_pv.MainView;


@Service
@Transactional
@Lazy
public class ClientController implements ClientService {

	private ClientRepository repository;
	private MainView view;

	private int idClient;
	private String name;
	private String surname;
	private String address;
	private String nationality;

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
	public void save() {
		Client client = new Client(view.getName(), view.getSurname(), view.getAddress(), view.getNationality());
		repository.save(client);
	}

	public void save(Client client) {
		repository.save(client);
	}

	@Override
	public void searchByIdClient(int idClient) {
		findByIdclient(idClient);
		if (!StringUtils.isEmpty(name)) {
			view.addClientColumns();
			view.showAccounts(idClient);
		}

	}

	@Override
	public void addClient() {

		if (view.isFilledClientCreateFields()) {
			refreshClient();
			save();
			view.clearTableColumns();
		}	
	}
	
	@Override
	public List<Client> findAll() {
		return repository.findAll();
	}

	@Override
	public void updateClient() {
		repository.updateClient(idClient, name, surname, address, nationality);
	}

	@Override
	public void updateClientProcess() {

		if (view.isClientDataFilled()) {
			refreshClient();
		}
	}
	
	@Override
	public int deleteByIdclient(int idclient) {
		return repository.deleteByIdclient(idclient);
	}


	@Override
	public void findByIdclient(int idClient) {
		Client client = repository.findByIdclient(idClient);
		if (StringUtils.isEmpty(name)) {
			clearTClientFields();
			view.setName("");
			view.setSurname("");
			view.setAddress("");
			view.setNationality("");
			view.setIdclient(0);
		} else {
			view.setName(client.getName());
			view.setSurname(client.getSurname());
			view.setAddress(client.getAddress());
			view.setNationality(client.getNationality());
			view.setIdclient(client.getIdclient());
		}

	}

	
	@Override
	public void refreshClient() {
		view.setIdclient(view.getIdSelected());
		view.setName(view.getTxtName());
		view.setSurname(view.getTxtSurname());
		view.setAddress(view.getTxtAddress());
		view.setNationality(view.getTxtNationality());
	}
	
	@Override
	public void clearTClientFields() {
		view.setTxtName("");
		view.setTxtSurname("");
		view.setTxtAddress("");
		view.setTxtNationality("");
	}
	
	@Override
	public void displayClientSelected(){
		view.setTxtName(view.getName());
		view.setTxtSurname(view.getSurname());
		view.setTxtAddress(view.getAddress());
		view.setTxtNationality(view.getNationality());
		view.setIdSelected(view.getIdclient());
	}

	
}
