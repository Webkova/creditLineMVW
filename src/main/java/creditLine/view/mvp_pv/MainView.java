package creditLine.view.mvp_pv;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

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
	TableView<Map> tblAccount;

	@FXML
	TableView<Map> tblClient;

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

	@FXML
	private TextField txtSearchById;

	private int idSelected = 0;

	private int idAccountSelected = 0;

	private int idclient;
	private String name;
	private String surname;
	private String address;
	private String nationality;

	private int idaccount;
	private String concept;
	private int accountType;
	private int accountStatus;
	private String creationDate;

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

	public int getIdaccount() {
		return idaccount;
	}

	public void setIdaccount(int idaccount) {
		this.idaccount = idaccount;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
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

	public void setTxtAccountType(String accountType) {
		this.txtAccountType.setText(accountType);
	}

	public TextField getTxtAccountStatus() {
		return txtAccountStatus;
	}

	public void setTxtAccountStatus(String accountStatus) {
		this.txtAccountStatus.setText(accountStatus);
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

	}

	@FXML
	void showClientSelected() {
		displayClientSelected();
	}

	public void displayClientSelected() {
		if (StringUtils.isEmpty(name)) {
			setTxtName(getName());
			setTxtSurname(getSurname());
			setTxtAddress(getAddress());
			setTxtNationality(getNationality());
			idSelected = getIdclient();

		}
	}

	@FXML
	void showAccountSelected() {
		displayAccountSelected();

	}

	public void displayAccountSelected() {
		if (StringUtils.isEmpty(concept)) {
			setTxtConcept(concept);
			setTxtAccountType(Integer.toString(accountType));
			setTxtAccountStatus(Integer.toString(accountStatus));
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addClientColumns() {
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

	@FXML
	void searchByIdClient() {
		clientService.searchByIdClient(idclient);
	}

	public void clearClientFields() {
		setTxtName("");
		setTxtSurname("");
		setTxtAddress("");
		setTxtNationality("");
	}

	@FXML
	public void addClient() {
		clientService.addClient();
	}

	public boolean isFilledClientCreateFields() {
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
			return true;
		}
		return false;
	}

	public void saveClient() {
		clientService.save();
	}

	public void clearTableColumns() {
		txtName.setText("");
		txtSurname.setText("");
		txtAddress.setText("");
		txtNationality.setText("");
	}

	@FXML
	void updateClient() {

		clientService.updateClientProcess();
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
		clientService.setIdClient(idSelected);
		clientService.setName(getTxtName().getText());
		clientService.setSurname(getTxtSurname().getText());
		clientService.setAddress(getTxtAddress().getText());
		clientService.setNationality(getTxtNationality().getText());
		clientService.updateClient();
	}

	@FXML
	void deleteClient() {
		if (!isDeleteClient()) {
			System.out.println("Error al intentar borrar el cliente");
		};
	}

	public boolean isDeleteClient() {
		if (StringUtils.isEmpty(concept)) {
			clientService.deleteByIdclient(idaccount);
			return true;
		}
		return false;
	}

	public void showAccounts(int idSelected) {
		accountService.getAccountByIdclient(idSelected);
		if (StringUtils.isEmpty(concept)) {
			clearAccountFields();
		}
		ObservableList<Map<String, Object>> items = this.getAccountData();
		tblAccount.getItems().addAll(items);
		this.getAccountsByID(tblAccount);

	}

	public ObservableList<Map<String, Object>> getAccountData() {
		ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

		Map<String, Object> map = new HashMap<>();
		map.put(idAccountColumnKey, idclient);
		map.put(conceptColumnKey, getConcept());
		map.put(accountTypeColumnKey, accountType);
		map.put(accountStatusColumnKey, accountStatus);
		map.put(creationDateAccountColumnKey, creationDate);

		items.add(map);

		return items;
	}

	public void clearAccountFields() {
		setTxtConcept("");
		setTxtAccountStatus("");
		setTxtAccountType("");
	}

	public void getAccountByIdClient(int idSelected) {
		accountService.getAccountByIdclient(idSelected);
	}

	@FXML
	public void updateTable() {
		ObservableList<Map<String, Object>> items = this.getClientData();
		tblClient.getItems().addAll(items);
		this.addClientColumns();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getAccountsByID(TableView<Map> tblAccount) {

		TableColumn<Map, Integer> idColumn = new TableColumn<>("ID Cuenta");
		idColumn.setCellValueFactory(new MapValueFactory<>(idAccountColumnKey));

		TableColumn<Map, String> conceptColumn = new TableColumn<>("Concepto");
		conceptColumn.setCellValueFactory(new MapValueFactory<>(conceptColumnKey));

		TableColumn<Map, Integer> accountTypeColumn = new TableColumn<>("Tipo de cuenta");
		accountTypeColumn.setCellValueFactory(new MapValueFactory<>(accountTypeColumnKey));

		TableColumn<Map, Integer> accountStatusColumn = new TableColumn<>("Estado de la cuenta");
		accountStatusColumn.setCellValueFactory(new MapValueFactory<>(accountStatusColumnKey));

		TableColumn<Map, String> creationDateColumn = new TableColumn<>("Fecha de creación");
		creationDateColumn.setCellValueFactory(new MapValueFactory<>(creationDateAccountColumnKey));

		tblAccount.getColumns().setAll(idColumn, conceptColumn, accountTypeColumn, accountStatusColumn,
				creationDateColumn);
	}

	public ObservableList<Map<String, Object>> getClientData() {
		ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

		Map<String, Object> map = new HashMap<>();
		map.put(idColumnKey, idclient);
		map.put(nameColumnKey, name);
		map.put(surnameColumnKey, surname);
		map.put(addressColumnKey, address);
		map.put(nationalityColumnKey, nationality);
		items.add(map);

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
		int idAccountSelected = idaccount;

		if (idAccountSelected != 0 || StringUtils.isEmpty(concept) || StringUtils.isEmpty(accountType)
				|| StringUtils.isEmpty(accountStatus)) {
			return true;
		}
		return false;
	}

	public void refreshAccount() {
		accountService.refreshAccount();
	}

	public void updateAccountData() {
		setConcept(getTxtConcept().getText());
		setAccountType(Integer.parseInt(getTxtAccountType().getText()));
		setAccountStatus(Integer.parseInt(getTxtAccountStatus().getText()));
	}

	public int getIdAccountSelected() {
		return idAccountSelected;
	}

	@FXML
	void addAccount() {
		accountService.addAccount();
	}

	public void saveAccountData() {
		updateAccountData();
		accountService.save();

	}

	@FXML
	void deleteAccount() {

		accountService.deleteAccount();
	}

	public void deleteByIdAccount(int idAccountSelected) {
		accountService.deleteByIdaccount(idaccount);
	}

}