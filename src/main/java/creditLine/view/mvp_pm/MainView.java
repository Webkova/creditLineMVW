package creditLine.view.mvp_pm;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import creditLine.persistence.entities.Account;
import creditLine.persistence.entities.Client;
import creditLine.services.mvp_pm.AccountService;
import creditLine.services.mvp_pm.ClientService;
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
    void displayClientSelected() {
		Client clientSelected = (Client) tblClient.getSelectionModel().getSelectedItem();
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
    void displayAccountSelected() {
		Account accountSelected = (Account) tblAccount.getSelectionModel().getSelectedItem();
		if (accountSelected != null) {
			setTxtConcept(accountSelected.getConcept());
			setTxtAccountType(accountSelected.getAccountType());
			setTxtAccountStatus(accountSelected.getAccountStatus());
			idAccountSelected = accountSelected.getIdaccount();
			
		}
    }
    
	@SuppressWarnings("unchecked")
	public void updateTable() {
		List<Client> clients = getAllClients();
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
	
	

	
	public List<Client> getAllClients() {
		return clientService.findAll();
	}
	
	@FXML
	public void addClient() {
		String name = getTxtName().getText();
		String surname = getTxtSurname().getText();
		String address = getTxtAddress().getText();
		String nationality = getTxtNationality().getText();

		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(surname) || StringUtils.isEmpty(address)
				|| StringUtils.isEmpty(nationality)) {
			return;
		}

		Client client = new Client(name, surname, address, nationality);
		clientService.save(client);
		clientData.add(client);

		clearTableColumns();

	}

	public void clearTableColumns() {
		txtName.setText("");
		txtSurname.setText("");
		txtAddress.setText("");
		txtNationality.setText("");
	}
	
	@FXML
	void updateClient() {
		String name = getTxtName().getText();
		String surname = getTxtSurname().getText();
		String address = getTxtAddress().getText();
		String nationality = getTxtNationality().getText();
		
		if (idSelected != 0 || StringUtils.isEmpty(name) || StringUtils.isEmpty(surname)
				|| StringUtils.isEmpty(address) || StringUtils.isEmpty(nationality)) {
			clientService.updateClient(idSelected, name, surname, address, nationality);
			updateTable();
		}
	}
	
	@FXML
	void deleteClient() {
		if (idSelected != 0) {
			clientService.deleteByIdclient(idSelected);
			updateTable();
		}
	}
	
    @SuppressWarnings("unchecked")
    void showAccounts(int idSelected) {
    	List<Account> accounts = accountService.getAccountByIdclient(idSelected);
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
    	String concept = getTxtConcept().getText();
    	int accountType = Integer.parseInt(getTxtAccountType().getText());
    	int accountStatus = Integer.parseInt(getTxtAccountStatus().getText());
    	
    	if (idAccountSelected != 0 || StringUtils.isEmpty(concept) || StringUtils.isEmpty(accountType)
				|| StringUtils.isEmpty(accountStatus)) {
	    		accountService.updateAccount(idAccountSelected, concept, accountType, accountStatus);
    	}		
    	
    	showAccounts(idSelected);
   }
    
    @FXML
    void addAccount() {
    	String concept = getTxtConcept().getText();
    	int accountType = Integer.parseInt(getTxtAccountType().getText());
    	int accountStatus = Integer.parseInt(getTxtAccountStatus().getText());
    	
    	if (idAccountSelected == 0 || StringUtils.isEmpty(concept) || StringUtils.isEmpty(accountType)
				|| StringUtils.isEmpty(accountStatus)) {
    		return;
    	}

		Account account = new Account(concept, accountType, accountStatus);
		accountService.save(account);
		accountData.add(account);
		
    }
    
    @FXML
    void deleteAccount() {
    	
		if (idAccountSelected != 0) {
			accountService.deleteByIdaccount(idAccountSelected);
			showAccounts(idAccountSelected);
		}
    }

}