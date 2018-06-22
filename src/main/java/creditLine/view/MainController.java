package creditLine.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import creditLine.persistence.entities.Client;
import creditLine.services.ClientService;

import javax.annotation.PostConstruct;

import java.util.List;

public class MainController {

	// Spring
	@Autowired
	private ClientService clientService;

	@FXML
	private AnchorPane rootPane;

	@FXML
	private TableView<Client> table;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtNationality;

	private ObservableList<Client> data;
	private int idSelected = 0;
	
	
	@FXML
	public void initialize() {

	}

	
	@PostConstruct
	public void init() {
		updateTable();
	}
	
	public void updateTable() {
		List<Client> clients = clientService.findAll();
		data = FXCollections.observableArrayList(clients);

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
		
		table.getColumns().setAll(idColumn, nameColumn, surnameColumn, addressColumn, nationalityColumn, creationDateColumn);

		table.setItems(data);
		idColumn.setSortType(TableColumn.SortType.DESCENDING);
	}

	@FXML
	public void addClient() {
		String name = txtName.getText();
		String surname = txtSurname.getText();
		String address = txtAddress.getText();
		String nationality = txtNationality.getText();
		
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(surname) || StringUtils.isEmpty(address)
				|| StringUtils.isEmpty(nationality)) {
			return;
		}

		Client client = new Client(name, surname, address, nationality);
		clientService.save(client);
		data.add(client);

		clearTableColumns();
		
	}

	public void clearTableColumns() {
		txtName.setText("");
		txtSurname.setText("");
		txtAddress.setText("");
	}

	@FXML
	void cancelClient() {
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.close();
	}
	
    @FXML
    void displaySelected(MouseEvent event) {
    Client clientSelected = table.getSelectionModel().getSelectedItem();
    	if (clientSelected != null) {
    		txtName.setText(clientSelected.getName());
    		txtSurname.setText(clientSelected.getSurname());
    		txtAddress.setText(clientSelected.getAddress());
    		txtNationality.setText(clientSelected.getNationality());
    		idSelected = clientSelected.getIdclient();
    	}

    }
    
    @FXML
    void deleteClient() {		
    	if (idSelected !=0) {
    		clientService.deleteByIdclient(idSelected);
    		updateTable();
    	}    	
    }


    @FXML
    void updateClient() {
    	if (idSelected != 0 ||StringUtils.isEmpty(txtName.getText()) || StringUtils.isEmpty(txtSurname.getText()) 
    			|| StringUtils.isEmpty(txtAddress.getText())
				|| StringUtils.isEmpty(txtNationality.getText())) {
    		clientService.updateClient(idSelected,txtName.getText());
    		updateTable();
		}  	
    }

}
