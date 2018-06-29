package creditLine.controllers.mvc;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import creditLine.listeners.HandleEvent;
import creditLine.persistence.daos.AccountRepository;
import creditLine.persistence.entities.Account;
import creditLine.services.AccountService;
import creditLine.view.MainView;


@Service
@Transactional
@Lazy
public class AccountController implements AccountService {
	
	private final AccountRepository repository;
	private MainView view;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	private final String CREATION = "acc_creation";
	private final String UPDATE = "acc_update";
	private final String READ = "acc_read";
	private final String DELETE = "acc_delete";
	
	@Autowired
	public AccountController(MainView view, AccountRepository repository, ApplicationEventPublisher applicationEventPublisher) {
		this.repository = repository;
		this.view = view;
		this.eventPublisher = eventPublisher;
	}
	
	@Override
	public Account save(Account account) {
		return repository.save(account);
	}

	@Override
	public void updateAccount(int idaccount,String concept, int accountType, int accountStatus) {
		repository.updateAccount(idaccount, concept, accountType, accountStatus);
		Account account = new Account(concept, accountType, accountStatus);
		HandleEvent event = new HandleEvent(this, UPDATE, account, idaccount);
		eventPublisher.publishEvent(event);
	}

	@Override
	public void getAccountByIdclient(int idaccount){
		Account account = repository.getAccountByIdclient(idaccount);
		HandleEvent event = new HandleEvent(this, READ, account);
		eventPublisher.publishEvent(event);
	}
	
	@Override
	public void deleteByIdaccount(int idaccount) {
		repository.deleteByIdaccount(idaccount);
		HandleEvent event = new HandleEvent(this, READ, idaccount);
		eventPublisher.publishEvent(event);
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
	public void addAccount(Account account) {
		save(account);
		HandleEvent event = new HandleEvent(this, CREATION, account);
		eventPublisher.publishEvent(event);
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
