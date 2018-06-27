package creditLine.controllers.mvc;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import creditLine.persistence.daos.AccountRepository;
import creditLine.persistence.entities.Account;
import creditLine.services.mvc.AccountService;
import creditLine.view.mvc.MainView;


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
	public Account getAccountByIdclient(int idclient){
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
		Account accounts = view.getAccountByIdClient(idSelected);
		if (StringUtils.isEmpty(accounts)){
			view.clearAccountFields();
		}
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
	
	@Override
	public Account findByIdaccount(int idaccount) {
		return repository.findByIdaccount(idaccount);
	}
	

}
