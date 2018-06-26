package creditLine.view.mvp_sc;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import creditLine.persistence.daos.mvp_sc.AccountRepository;
import creditLine.persistence.daos.mvp_sc.ClientRepository;
import creditLine.persistence.entities.Account;
import creditLine.persistence.entities.Client;
import creditLine.services.mvp_sc.AccountService;
import creditLine.services.mvp_sc.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class MainView {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	@FXML
	private HBox hboxTblAccounts;

	@FXML
	private TableView<Account> tblAccount;

	@FXML
	private TableView<Client> tblClient;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtSurname;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtNationality;

	@FXML
	private TextField txtConcept;

	@FXML
	private TextField txtAccountType;

	@FXML
	private TextField txtAccountStatus;

	private ObservableList<Account> accountData;

	private ObservableList<Client> clientData;

	private int idSelected = 0;

	private int idAccountSelected = 0;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public TextField getTxtName() {
		return txtName;
	}

	public void setTxtName(String name) {
		this.txtName.setText(name);
	}

	public TextField getTxtSurname() {
		return txtSurname;
	}

	public void setTxtSurname(String surname) {
		this.txtSurname.setText(surname);
	}

	public TextField getTxtAddress() {
		return txtAddress;
	}

	public void setTxtAddress(String address) {
		this.txtAddress.setText(address);
	}

	public TextField getTxtNationality() {
		return txtNationality;
	}

	public void setTxtNationality(String nationality) {
		this.txtNationality.setText(nationality);
	}

	public TextField getTxtConcept() {
		return txtConcept;
	}

	public void setTxtConcept(String concept) {
		this.txtConcept.setText(concept);
	}

	public TextField getTxtAccountType() {
		return txtAccountType;
	}

	public void setTxtAccountType(int accountType) {
		this.txtAccountType.setText(Integer.toString(accountType));
	}

	public TextField getTxtAccountStatus() {
		return txtAccountStatus;
	}

	public void setTxtAccountStatus(int accountStatus) {
		this.txtAccountStatus.setText(Integer.toString(accountStatus));
	}

	@FXML
	public void initialize() {

	}

	public void setClientTable(TableView<Client> tblClient) {
		this.tblClient = tblClient;
	}

	@PostConstruct
	public void init() {
		updateTable();
	}

	@FXML
	void showClientSelected() {
		clientService.showClientSelected();

	}

	public Client getClientSelected() {
		return (Client) tblClient.getSelectionModel().getSelectedItem();
	}

	public void displayClientSelected(Client clientSelected) {
		if (clientSelected != null) {
			setTxtName(clientSelected.getName());
			setTxtSurname(clientSelected.getSurname());
			setTxtAddress(clientSelected.getAddress());
			setTxtNationality(clientSelected.getNationality());
			idSelected = clientSelected.getIdclient();
			
			showAccounts(idSelected);
		}
	}

	@FXML
	void showAccountSelected() {
		accountService.showAccountSelected();

	}

	public Account getAccounts() {
		return (Account) tblAccount.getSelectionModel().getSelectedItem();
	}

	public void displayAccountSelected(Account accountSelected) {
		if (accountSelected != null) {
			setTxtConcept(accountSelected.getConcept());
			setTxtAccountType(accountSelected.getAccountType());
			setTxtAccountStatus(accountSelected.getAccountStatus());
			idAccountSelected = accountSelected.getIdaccount();
		}
	}

	@FXML
	public void updateTable() {		
		List<Client> clients = getAllClients() ;
		clientService.updateClientTable(clients);

	}

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@SuppressWarnings("unchecked")
	public void updateClientTableView(List<Client> clients) {
		clientData = FXCollections.observableArrayList(clients);

		TableColumn<Client, String> idColumn = new TableColumn<>("ID Cliente");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("idclient"));

		TableColumn<Client, String> nameColumn = new TableColumn<>("Nombre");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Client, String> surnameColumn = new TableColumn<>("Apellidos");
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

		TableColumn<Client, String> addressColumn = new TableColumn<>("Dirección");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

		TableColumn<Client, String> nationalityColumn = new TableColumn<>("Nacionalidad");
		nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));

		TableColumn<Client, String> creationDateColumn = new TableColumn<>("Fecha creación");
		creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

		tblClient.getColumns().setAll(idColumn, nameColumn, surnameColumn, addressColumn, nationalityColumn,
				creationDateColumn);

		tblClient.setItems(clientData);
		idColumn.setSortType(TableColumn.SortType.DESCENDING);
	}


	@FXML
	public void addClient() {
		clientService.addClient();
	}

	public Client createClient() {
		String name = getTxtName().getText();
		String surname = getTxtSurname().getText();
		String address = getTxtAddress().getText();
		String nationality = getTxtNationality().getText();

		if (!StringUtils.isEmpty(name) || !StringUtils.isEmpty(surname) || !StringUtils.isEmpty(address)
				|| !StringUtils.isEmpty(nationality)) {
			return new Client(name, surname, address, nationality);
		}
		return null;
	}

	public void saveClient(Client client) {
		clientRepository.save(client);
		clientData.add(client);
	}

	public void clearTableColumns() {
		txtName.setText("");
		txtSurname.setText("");
		txtAddress.setText("");
		txtNationality.setText("");
	}

	@FXML
	void updateClient() {
		clientService.updateClient();
	}

	public boolean isClientDataFilled() {
		String name = getTxtName().getText();
		String surname = getTxtSurname().getText();
		String address = getTxtAddress().getText();
		String nationality = getTxtNationality().getText();

		if (idSelected != 0 || StringUtils.isEmpty(name) || StringUtils.isEmpty(surname) || StringUtils.isEmpty(address)
				|| StringUtils.isEmpty(nationality)) {
			return true;
		}
		return false;
	}

	public void refreshClient() {
		clientRepository.updateClient(idSelected, getTxtName().getText(), getTxtSurname().getText(),
				getTxtAddress().getText(), getTxtNationality().getText());
	}

	@FXML
	void deleteClient() {
		clientService.deleteClient();
	}

	public boolean isDeleteClient() {
		if (idSelected != 0) {
			clientRepository.deleteByIdclient(idSelected);
			return true;
		}
		return false;
	}

	public void showAccounts(int idSelected) {
		accountService.showAccounts(idSelected);
	}

	public List<Account> getAccountByIdClient(int idSelected) {
		return accountRepository.getAccountByIdclient(idSelected);
	}

	@SuppressWarnings("unchecked")
	public void getAccountsByID(List<Account> accounts) {

		accountData = FXCollections.observableArrayList(accounts);

		tblAccount.setItems(accountData);

		TableColumn<Account, String> idaccountColumn = new TableColumn<>("ID Cuenta");
		idaccountColumn.setCellValueFactory(new PropertyValueFactory<>("idaccount"));

		TableColumn<Account, String> conceptColumn = new TableColumn<>("Concepto");
		conceptColumn.setCellValueFactory(new PropertyValueFactory<>("concept"));

		TableColumn<Account, String> accountTypeColumn = new TableColumn<>("Tipo de Cuenta");
		accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));

		TableColumn<Account, String> accountStatusColumn = new TableColumn<>("Estado");
		accountStatusColumn.setCellValueFactory(new PropertyValueFactory<>("accountStatus"));

		TableColumn<Account, String> creationDateColumn = new TableColumn<>("Fecha de Creación");
		creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

		tblAccount.getColumns().setAll(idaccountColumn, conceptColumn, accountTypeColumn, accountStatusColumn,
				creationDateColumn);

		idaccountColumn.setSortType(TableColumn.SortType.DESCENDING);
	}

	@FXML
	void updateAccount() {
		accountService.updateAccount();
	}

	public boolean isFilledAccountData() {
		String concept = getTxtConcept().getText();
		int accountType = Integer.parseInt(getTxtAccountType().getText());
		int accountStatus = Integer.parseInt(getTxtAccountStatus().getText());

		if (idAccountSelected != 0 || StringUtils.isEmpty(concept) || StringUtils.isEmpty(accountType)
				|| StringUtils.isEmpty(accountStatus)) {
			return true;
		}
		return false;
	}
	
	public void refreshAccount() {
		accountRepository.updateAccount(idAccountSelected, getTxtConcept().getText(), Integer.parseInt(getTxtAccountType().getText()), 
				Integer.parseInt(getTxtAccountStatus().getText()));
	}
	
	public int getIdAccountSelected() {
		return idAccountSelected;
	}
	
	
	@FXML
	void addAccount() {
		accountService.saveAccount();
	}
	
	public void saveAccountData() {
		Account account = new Account(getTxtConcept().getText(), Integer.parseInt(getTxtAccountType().getText()), 
				Integer.parseInt(getTxtAccountStatus().getText()));
		accountRepository.save(account);
		accountData.add(account);
	}

	@FXML
	void deleteAccount() {
		
		if (idAccountSelected != 0) {
			accountRepository.deleteByIdaccount(idAccountSelected);
			showAccounts(idAccountSelected);
		}
	}
	
	public void deleteByIdAccount(int idAccountSelected) {
		accountRepository.deleteByIdaccount(idAccountSelected);
	}

}