package creditLine.view.mvp_pm;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import creditLine.services.mvp_pm.AccountService;
import creditLine.services.mvp_pm.ClientService;
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
			setTxtName(name);
			setTxtSurname(surname);
			setTxtAddress(address);
			setTxtNationality(nationality);
			idSelected = idclient;
			
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

		tblClient.getColumns().setAll(idColumn, nameColumn, surnameColumn, addressColumn, nationalityColumn,creationDateColumn);
	}
	
	
	
    @FXML
    void searchByIdClient() {
    	clientService.findByIdclient(Integer.parseInt(txtSearchById.getText()));
    	
     	if (StringUtils.isEmpty(clientService.getName())) {
    		clearClientFields(); 
    	}else {
    		name = clientService.getName();
        	surname = clientService.getSurname();
        	address = clientService.getAddress();
        	nationality = clientService.getNationality();
    	}

    	addClientColumns(tblClient);
    	showAccounts(idclient);
    	
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

		if (!StringUtils.isEmpty(getTxtName().getText()) || !StringUtils.isEmpty(getTxtSurname().getText()) || 
				!StringUtils.isEmpty(getTxtAddress().getText()) || !StringUtils.isEmpty(getTxtNationality().getText())) {
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
		boolean isUpdated = isClientDataFilled();
		if (isUpdated) {
			refreshClient();
		}
	}

	public boolean isClientDataFilled() {

		if (idSelected != 0 || StringUtils.isEmpty(getTxtName().getText()) || StringUtils.isEmpty(getTxtSurname().getText()) || StringUtils.isEmpty(getTxtAddress().getText())
				|| StringUtils.isEmpty(getTxtNationality().getText())) {
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
		if (StringUtils.isEmpty(idclient)) {
			clientService.deleteByIdclient(idaccount);
		}

	}

	public void showAccounts(int idSelected) {
		accountService.getAccountByIdclient(idaccount);
		if (StringUtils.isEmpty(concept)){
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
			map.put(conceptColumnKey, concept);
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
		this.addClientColumns(tblClient);
	}

	@SuppressWarnings("unchecked")
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

		if (idAccountSelected != 0 || StringUtils.isEmpty(getTxtConcept().getText()) || StringUtils.isEmpty(Integer.parseInt(getTxtAccountType().getText()))
				|| StringUtils.isEmpty(Integer.parseInt(getTxtAccountStatus().getText()))) {
			return true;
		}
		return false;
	}
	
	public void refreshAccount() {
		idaccount = idAccountSelected;
		updateAccountData();
		accountService.updateAccount();
	}
	
	public void updateAccountData() {
		concept = getTxtConcept().getText();
		accountType = Integer.parseInt(getTxtAccountType().getText());
		accountStatus = Integer.parseInt(getTxtAccountStatus().getText());
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
		updateAccountData();
		accountService.save();

	}

	@FXML
	void deleteAccount() {
		
		if (StringUtils.isEmpty(idaccount)) {
			accountService.deleteByIdaccount(idAccountSelected);
			showAccounts(idaccount);
		}
	}
	
	public void deleteByIdAccount(int idAccountSelected) {
		accountService.deleteByIdaccount(idaccount);
	}
	
}