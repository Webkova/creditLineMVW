package creditLine.controllers.mvp_sc;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import creditLine.persistence.daos.mvp_sc.AccountRepository;
import creditLine.persistence.entities.Account;
import creditLine.services.mvp_sc.AccountService;
import creditLine.view.mvp_sc.MainView;


@Service
@Transactional
@Lazy
public class AccountController implements AccountService {
	
	private final AccountRepository repository;
	private MainView view;
	
	@Autowired
	public AccountController(MainView view, AccountRepository repository) {
		this.repository = repository;
		this.view = view;
	}
	
	@Override
	public Account save(Account account) {
		return repository.save(account);
	}

	@Override
	public void updateAccount(int idaccount,String concept, int accountType, int accountStatus) {
		repository.updateAccount(idaccount, concept, accountType, accountStatus);
	}

	@Override
	public List<Account> getAccountByIdclient(int idclient){
		return repository.getAccountByIdclient(idclient);
	}
	
	@Override
	public void deleteByIdaccount(int idaccount) {
		repository.deleteByIdaccount(idaccount);
	}

	@Override
	public void showAccountSelected() {
		Account accountSelected = view.getAccounts();
		view.displayAccountSelected(accountSelected);
	}

	@Override
	public void showAccounts(int idSelected) {
		List<Account> accounts = view.getAccountByIdClient(idSelected);
		view.getAccountsByID(accounts);
		
	}

	@Override
	public void updateAccount() {
		if (view.isFilledAccountData()) {
			view.refreshAccount();
			view.showAccounts(view.getIdAccountSelected());
		}
		
	}

	@Override
	public void saveAccount() {
		if (view.isFilledAccountData()) {
			view.saveAccountData();
		}
		
	}

	@Override
	public void deleteAccount() {
		int idAccountSelected = view.getIdAccountSelected();
		view.deleteByIdAccount(idAccountSelected);
		showAccounts(idAccountSelected);
		
	}
	

}