package creditLine.view.mvvm;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import creditLine.services.mvvm.AccountService;
import creditLine.services.mvvm.ClientService;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
	
	private IntegerProperty idclientBinder = new SimpleIntegerProperty();
	private StringProperty nameBinder = new SimpleStringProperty();
	private StringProperty surnameBinder = new SimpleStringProperty();
	private StringProperty addressBinder = new SimpleStringProperty();
	private StringProperty nationalityBinder = new SimpleStringProperty();
	
	private IntegerProperty idaccountBinder = new SimpleIntegerProperty();
	private StringProperty conceptBinder = new SimpleStringProperty();
	private IntegerProperty accountTypeBinder = new SimpleIntegerProperty();
	private IntegerProperty accountStatusBinder = new SimpleIntegerProperty();
	private StringProperty creationDateBinder = new SimpleStringProperty();
	
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
	
	
	public IntegerProperty getIdclientBinder() {
		return idclientBinder;
	}

	public void setIdclientBinder(IntegerProperty idclientBinder) {
		this.idclientBinder = idclientBinder;
	}

	public StringProperty getNameBinder() {
		return nameBinder;
	}

	public void setNameBinder(StringProperty nameBinder) {
		this.nameBinder = nameBinder;
	}

	public StringProperty getSurnameBinder() {
		return surnameBinder;
	}

	public void setSurnameBinder(StringProperty surnameBinder) {
		this.surnameBinder = surnameBinder;
	}

	public StringProperty getAddressBinder() {
		return addressBinder;
	}

	public void setAddressBinder(StringProperty addressBinder) {
		this.addressBinder = addressBinder;
	}

	public StringProperty getNationalityBinder() {
		return nationalityBinder;
	}

	public void setNationalityBinder(StringProperty nationalityBinder) {
		this.nationalityBinder = nationalityBinder;
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
		nameBinder.bindBidirectional(clientService.getNameBinder());
		surnameBinder.bindBidirectional(clientService.getSurnameBinder());
		addressBinder.bindBidirectional(clientService.getAddressBinder());
		nationalityBinder.bindBidirectional(clientService.getNationalityBinder());
		
		idaccountBinder.bindBidirectional(accountService.getIdaccount());
		conceptBinder.bindBidirectional(accountService.getConcept());
		accountStatusBinder.bindBidirectional(accountService.getAccountStatus());
		accountTypeBinder.bindBidirectional(accountService.getAccountType());
		creationDateBinder.bindBidirectional(accountService.getCreationDate());
	}

	@FXML
	void showClientSelected() {
		displayClientSelected();
	}


	public void displayClientSelected() {
		if (StringUtils.isEmpty(nameBinder)) {
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
		if (StringUtils.isEmpty(conceptBinder)) {
			setTxtConcept(conceptBinder.getValue());
			setTxtAccountType(Integer.toString(accountTypeBinder.getValue()));
			setTxtAccountStatus(Integer.toString(accountStatusBinder.getValue()));
		}
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
	
	
	
    @FXML
    void searchByIdClient() {
    	clientService.findByIdclient(Integer.parseInt(txtSearchById.getText()));

    	if (StringUtils.isEmpty(nameBinder)) {
    		clearClientFields(); 
    	}

    	addClientColumns(tblClient);
    	showAccounts(idclientBinder.getValue());
    	
    }
    
     
    public void clearClientFields() {
    	setTxtName("");
    	setTxtSurname("");
    	setTxtAddress("");
    	setTxtNationality("");
    }

	@FXML
	public void addClient() {
		if (isFilledClientCreateFields()) {
			saveClient();
			clearTableColumns();
		}	
	}

	public boolean isFilledClientCreateFields() {
		String name = getTxtName().getText();
		String surname = getTxtSurname().getText();
		String address = getTxtAddress().getText();
		String nationality = getTxtNationality().getText();

		if (!StringUtils.isEmpty(name) || !StringUtils.isEmpty(surname) || !StringUtils.isEmpty(address)
				|| !StringUtils.isEmpty(nationality)) {
			nameBinder.setValue(name);
			surnameBinder.setValue(surname);
			addressBinder.setValue(address);
			nationalityBinder.setValue(nationality);
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
		boolean isUpdated = isClientDataFilled();
		if (isUpdated) {
			refreshClient();
		}
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
		idclientBinder.setValue(idSelected);
		nameBinder.setValue(getTxtName().getText());
		surnameBinder.setValue(getTxtSurname().getText());
		addressBinder.setValue(getTxtAddress().getText());
		nationalityBinder.setValue(getTxtNationality().getText());
		clientService.updateClient();
	}

	@FXML
	void deleteClient() {
		if (StringUtils.isEmpty(conceptBinder)) {
			clientService.deleteByIdclient(idaccountBinder.getValue());
		}
	}


	public void showAccounts(int idSelected) {
		accountService.getAccountByIdclient(idaccountBinder.getValue());
		if (StringUtils.isEmpty(conceptBinder)){
			clearAccountFields();
		}
		ObservableList<Map<String, Object>> items = this.getAccountData();
		tblAccount.getItems().addAll(items);
		this.getAccountsByID(tblAccount);

	}
	
	public ObservableList<Map<String, Object>> getAccountData() {
		ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

			Map<String, Object> map = new HashMap<>();
			map.put(idAccountColumnKey, idclientBinder.getValue());
			map.put(conceptColumnKey, conceptBinder.getValue());
			map.put(accountTypeColumnKey, accountTypeBinder.getValue());
			map.put(accountStatusColumnKey, accountStatusBinder.getValue());
			map.put(creationDateAccountColumnKey, creationDateBinder.getValue());
			//map.put(creationDateColumnKey, getCreationDate());
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
		this.addClientColumns(tblClient);
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
			map.put(idColumnKey, idclientBinder.getValue());
			map.put(nameColumnKey, nameBinder.getValue());
			map.put(surnameColumnKey, surnameBinder.getValue());
			map.put(addressColumnKey, addressBinder.getValue());
			map.put(nationalityColumnKey, nationalityBinder.getValue());
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

		if (idAccountSelected != 0 || StringUtils.isEmpty(concept) || StringUtils.isEmpty(accountType)
				|| StringUtils.isEmpty(accountStatus)) {
			return true;
		}
		return false;
	}
	
	public void refreshAccount() {
		idaccountBinder.setValue(idAccountSelected);
		updateAccountDataBinder();
		accountService.updateAccount();
	}
	
	public void updateAccountDataBinder() {
		conceptBinder.setValue(getTxtConcept().getText());
		accountTypeBinder.setValue(Integer.parseInt(getTxtAccountType().getText()));
		accountStatusBinder.setValue(Integer.parseInt(getTxtAccountStatus().getText()));
	}
	
	public int getIdAccountSelected() {
		return idAccountSelected;
	}
	
	
	@FXML
	void addAccount() {
		if (isFilledAccountData()) {
			refreshAccount();
		}
	}
	
	public void saveAccountData() {
		updateAccountDataBinder();
		accountService.save();

	}

	@FXML
	void deleteAccount() {
		
		if (StringUtils.isEmpty(idaccountBinder)) {
			accountService.deleteByIdaccount(idAccountSelected);
			showAccounts(idaccountBinder.getValue());
		}
	}
	
	public void deleteByIdAccount(int idAccountSelected) {
		accountService.deleteByIdaccount(idaccountBinder.getValue());
	}
	
}