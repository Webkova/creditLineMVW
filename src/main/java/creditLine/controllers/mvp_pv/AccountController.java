package creditLine.controllers.mvp_pv;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import creditLine.persistence.daos.AccountRepository;
import creditLine.persistence.entities.Account;
import creditLine.services.mvp_pv.AccountService;
import creditLine.view.mvp_pv.MainView;


@Service
@Transactional
@Lazy
public class AccountController implements AccountService {
	
	private final AccountRepository repository;
	private MainView view;
	
	private int idaccount;
	private String concept;
	private int accountType;
	private int accountStatus;
	private String creationDate;
	
	@Autowired
	public AccountController(MainView view, AccountRepository repository) {
		this.repository = repository;
		this.view = view;
	}
	
	@Override
	public Account save() {
		Account account = new Account(concept, accountType, accountStatus);
		return repository.save(account);
	}

	@Override
	public void updateAccount() {
		repository.updateAccount(view.getIdaccount(), view.getConcept(), view.getAccountType(), 
								view.getAccountStatus());
	}

	@Override
	public void addAccount() {
		setAccountAttributes();
		if (view.isFilledAccountData()) {
			view.refreshAccount();
		}		
	}
	
	@Override
	public void getAccountByIdclient(int idclient){
		Account account = repository.getAccountByIdclient(idclient);
		view.setIdaccount(account.getIdaccount());
		view.setConcept(account.getConcept());
		view.setAccountStatus(account.getAccountStatus());
		view.setAccountType(account.getAccountType());
		view.setCreationDate(account.getCreationDate());
	}
	
	@Override
	public void deleteByIdaccount(int idaccount) {
		repository.deleteByIdaccount(idaccount);
	}


	@Override
	public void saveAccount() {
		setAccountAttributes();
		if (view.isFilledAccountData()) {
			save();
		}
		
	}

	@Override
	public void deleteAccount() {
		if (StringUtils.isEmpty(view.getIdaccount())) {
			deleteByIdaccount(view.getIdaccount());
			view.showAccounts(view.getIdaccount());
		}
		
	}
	
	@Override
	public Account findByIdaccount(int idaccount) {
		return repository.findByIdaccount(idaccount);
	}

	
	@Override
	public void refreshAccount() {
		view.setIdaccount(view.getIdaccount());
		setAccountAttributes();
		updateAccount();
	}

	@Override
	public void displayAccountSelected() {
		view.setTxtConcept(view.getConcept());
		view.setTxtAccountType(Integer.toString(view.getAccountType()));
		view.setTxtAccountStatus(Integer.toString(view.getAccountStatus()));
	}
	
	@Override
	public void clearAccountFields() {
		view.setTxtConcept("");
		view.setTxtAccountStatus("");
		view.setTxtAccountType("");
	}
	
	@Override
	public void setAccountAttributes() {
		view.setConcept(view.getTxtConcept());
		view.setAccountType(Integer.parseInt(view.getTxtAccountType()));
		view.setAccountStatus(Integer.parseInt(view.getTxtAccountStatus()));
		view.setIdSelected(view.getIdaccount());
	}

}
