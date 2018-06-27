package creditLine.controllers.mvvm;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import creditLine.persistence.daos.AccountRepository;
import creditLine.persistence.entities.Account;
import creditLine.services.mvvm.AccountService;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Service
@Transactional
@Lazy
public class AccountController implements AccountService {
	
	private final AccountRepository repository;

	
	private IntegerProperty idaccount = new SimpleIntegerProperty();
	private StringProperty concept = new SimpleStringProperty();
	private IntegerProperty accountType = new SimpleIntegerProperty();
	private IntegerProperty accountStatus = new SimpleIntegerProperty();
	private StringProperty creationDate = new SimpleStringProperty();
	
	@Autowired
	public AccountController(AccountRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Account save() {
		Account account = new Account(concept.getValue(), accountType.getValue(), accountStatus.getValue());
		return repository.save(account);
	}

	@Override
	public void updateAccount() {
		repository.updateAccount(idaccount.getValue(), concept.getValue(), accountType.getValue(), 
								accountStatus.getValue());
	}

	@Override
	public void getAccountByIdclient(int idclient){
		Account account = repository.getAccountByIdclient(idclient);
		idaccount.setValue(account.getIdaccount());
		concept.setValue(account.getConcept());
		accountStatus.setValue(account.getAccountStatus());
		accountType.setValue(account.getAccountType());
		creationDate.setValue(account.getCreationDate());
	}
	
	@Override
	public void deleteByIdaccount(int idaccount) {
		repository.deleteByIdaccount(idaccount);
	}

	@Override
	public Account findByIdaccount(int idaccount) {
		return repository.findByIdaccount(idaccount);
	}

	@Override
	public IntegerProperty getIdaccount() {
		return idaccount;
	}

	@Override
	public void setIdaccount(IntegerProperty idaccount) {
		this.idaccount = idaccount;
	}

	@Override
	public StringProperty getConcept() {
		return concept;
	}

	@Override
	public void setConcept(StringProperty concept) {
		this.concept = concept;
	}

	@Override
	public IntegerProperty getAccountType() {
		return accountType;
	}

	@Override
	public void setAccountType(IntegerProperty accountType) {
		this.accountType = accountType;
	}

	@Override
	public IntegerProperty getAccountStatus() {
		return accountStatus;
	}

	@Override
	public void setAccountStatus(IntegerProperty accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public StringProperty getCreationDate() {
		return creationDate;
	}

	@Override
	public void setCreationDate(StringProperty creationDate) {
		this.creationDate = creationDate;
	}
	
	

}
