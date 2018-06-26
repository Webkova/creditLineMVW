package creditLine.view.mvp_pv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import creditLine.persistence.entities.Account;
import creditLine.persistence.entities.Client;
import creditLine.services.mvp_pv.AccountService;
import creditLine.services.mvp_pv.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;

public class MainView {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ClientService clientService;

	@FXML
	private HBox hboxTblAccounts;

	@FXML
	private TableView<Map> tblAccount;

	@FXML
	private TableView<Map> tblClient;

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

	private int idSelected = 0;

	private int idAccountSelected = 0;

	private int idclient;
	private String name;
	private String surname;
	private String address;
	private String nationality;
	private String creationDate;
	
	private int idAccount;
	private String concept;
	private String accountType;
	private String accountStatus;
	private String creationDateAccount;

	private final String idColumnKey = "idclient";
	private final String nameColumnKey = "name";
	private final String surnameColumnKey = "surname";
	private final String addressColumnKey = "address";
	private final String nationalityColumnKey = "nationality";
	private final String creationDateColumnKey = "creationDate";
	
	private final String idAccountColumnKey = "idaccount";
	private final String conceptColumnKey = "concept";
	private final String accountTypeColumnKey = "accountType";
	private final String accountStatusColumnKey = "accountStatus";
	private final String creationDateAccountColumnKey = "creationDate";

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

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
	
	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCreationDateAccount() {
		return creationDateAccount;
	}

	public void setCreationDateAccount(String creationDateAccount) {
		this.creationDateAccount = creationDateAccount;
	}

	@FXML
	public void initialize() {

	}

	@SuppressWarnings("rawtypes")
	public void setClientTable(TableView<Map> tblClient) {
		this.tblClient = tblClient;
	}

	@PostConstruct
	public void init() {
		clientService.updateClientTable();
	}

	@FXML
	void showClientSelected() {
		clientService.showClientSelected();

	}

	public Map getClientSelected() {
		return (Map) tblClient.getSelectionModel().getSelectedItem();
	}

	public void displayClientSelected(Map clientSelected) {
		if (clientSelected != null) {
			setTxtName(clientSelected.get(1).toString());
			setTxtSurname(clientSelected.get(2).toString());
			setTxtAddress(clientSelected.get(3).toString());
			setTxtNationality(clientSelected.get(4).toString());
			idSelected = Integer.parseInt(clientSelected.get(0).toString());

			showAccounts(idSelected);
		}
	}

	@FXML
	void showAccountSelected() {
		accountService.showAccountSelected();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> getAccounts() {
		return (List<Map>) tblAccount.getSelectionModel().getSelectedItem();
	}

	public void displayAccountSelected(Map accountSelected) {
		if (accountSelected != null) {
			setTxtConcept(accountSelected.get(1).toString());
			setTxtAccountType(Integer.parseInt(accountSelected.get(2).toString()));
			setTxtAccountStatus(Integer.parseInt(accountSelected.get(3).toString()));
			idAccountSelected = Integer.parseInt(accountSelected.get(0).toString());
		}
	}

	@FXML
	public void updateTable() {
		clientService.updateClientTable();

	}

	public List<Client> getClients() {
		return getAllClients();
	}

	public void updateClientTableView() {

		ObservableList<Map<String, Object>> items = this.getClientData(2);
		tblClient.getItems().addAll(items);
		this.addClientColumns(tblClient);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addClientColumns(TableView<Map> tblClient) {

		TableColumn<Map, Integer> idColumn = new TableColumn<>("ID Cliente");
		idColumn.setCellValueFactory(new MapValueFactory<>(idColumnKey));

		TableColumn<Map, String> nameColumn = new TableColumn<>("Nombre");
		nameColumn.setCellValueFactory(new MapValueFactory<>(nameColumnKey));

		TableColumn<Map, String> surnameColumn = new TableColumn<>("Apellidos");
		surnameColumn.setCellValueFactory(new MapValueFactory<>(surnameColumnKey));

		TableColumn<Map, String> addressColumn = new TableColumn<>("Dirección");
		addressColumn.setCellValueFactory(new MapValueFactory<>(addressColumnKey));

		TableColumn<Map, String> nationalityColumn = new TableColumn<>("Nacionalidad");
		nationalityColumn.setCellValueFactory(new MapValueFactory<>(nationalityColumnKey));

		TableColumn<Map, String> creationDateColumn = new TableColumn<>("Fecha creación");
		creationDateColumn.setCellValueFactory(new MapValueFactory<>(creationDateColumnKey));

		tblClient.getColumns().setAll(idColumn, nameColumn, surnameColumn, addressColumn, nationalityColumn,
				creationDateColumn);
	}

	public ObservableList<Map<String, Object>> getClientData(int size) {
		ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

		for (int i = 0; i < size; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put(idColumnKey, getIdclient());
			map.put(nameColumnKey, getName());
			map.put(surnameColumnKey, getSurname());
			map.put(addressColumnKey, getAddress());
			map.put(nationalityColumnKey, getNationality());
			map.put(creationDateColumnKey, getCreationDate());
			items.add(map);
		}
		return items;
	}

	public List<Client> getAllClients() {
		return clientService.findAll();
	}

	@FXML
	public void addClient() {
		clientService.addClient();
	}

	public boolean isDataTocreateClient() {
		String name = getTxtName().getText();
		String surname = getTxtSurname().getText();
		String address = getTxtAddress().getText();
		String nationality = getTxtNationality().getText();

		if (!StringUtils.isEmpty(name) || !StringUtils.isEmpty(surname) || !StringUtils.isEmpty(address)
				|| !StringUtils.isEmpty(nationality)) {
			clientService.setName(name);
			clientService.setSurname(surname);
			clientService.setAddress(address);
			clientService.setNationality(nationality);
			return  true;
		}
		return false;
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
		clientService.updateClient(idSelected, getTxtName().getText(), getTxtSurname().getText(),
				getTxtAddress().getText(), getTxtNationality().getText());
	}

	@FXML
	void deleteClient() {
		clientService.deleteClient();
	}

	public boolean isDeleteClient() {
		if (idSelected != 0) {
			clientService.deleteByIdclient(idSelected);
			return true;
		}
		return false;
	}

	public void showAccounts(int idSelected) {
		accountService.showAccounts(idSelected);
	}

	public List<Account> getAccountByIdClient(int idSelected) {
		return accountService.getAccountByIdclient(idSelected);
	}

	
	public void getAccountsByID(List<Map> accounts) {

		ObservableList<Map<String, Object>> items = this.getAccountData(2);
		tblAccount.getItems().addAll(items);
		this.addAccountColumns(tblAccount);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addAccountColumns(TableView<Map> tblAccount) {
		
		TableColumn<Map, Integer> idaccountColumn = new TableColumn<>("ID Cuenta");
		idaccountColumn.setCellValueFactory(new MapValueFactory<>("idaccount"));

		TableColumn<Map, String> conceptColumn = new TableColumn<>("Concepto");
		conceptColumn.setCellValueFactory(new MapValueFactory<>("concept"));

		TableColumn<Map, Integer> accountTypeColumn = new TableColumn<>("Tipo de Cuenta");
		accountTypeColumn.setCellValueFactory(new MapValueFactory<>("accountType"));

		TableColumn<Map, Integer> accountStatusColumn = new TableColumn<>("Estado");
		accountStatusColumn.setCellValueFactory(new MapValueFactory<>("accountStatus"));

		TableColumn<Map, String> creationDateColumn = new TableColumn<>("Fecha de Creación");
		creationDateColumn.setCellValueFactory(new MapValueFactory<>("creationDate"));

		tblAccount.getColumns().setAll(idaccountColumn, conceptColumn, accountTypeColumn, accountStatusColumn,
				creationDateColumn);
	}
	
	public ObservableList<Map<String, Object>> getAccountData(int size) {
		ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

		for (int i = 0; i < size; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put(idAccountColumnKey, getIdAccount());
			map.put(conceptColumnKey, getConcept());
			map.put(accountTypeColumnKey, getAccountType());
			map.put(accountStatusColumnKey, getAccountStatus());
			map.put(creationDateAccountColumnKey, getCreationDate());
		
			items.add(map);
		}
		return items;
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
		accountService.updateAccount(idAccountSelected, getTxtConcept().getText(),
				Integer.parseInt(getTxtAccountType().getText()), Integer.parseInt(getTxtAccountStatus().getText()));
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
		accountService.save(account);
		accountData.add(account);
	}

	@FXML
	void deleteAccount() {
		System.out.println(idAccountSelected);
		if (idAccountSelected != 0) {
			accountService.deleteByIdaccount(idAccountSelected);
			showAccounts(idAccountSelected);
		}
	}

	public void deleteByIdAccount(int idAccountSelected) {
		accountService.deleteByIdaccount(idAccountSelected);
	}

}