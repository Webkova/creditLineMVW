package creditLine.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import creditLine.persistence.daos.AccountRepository;
import creditLine.persistence.entities.Account;
import creditLine.services.AccountService;


@Service
@Transactional
public class AccountController implements AccountService {
	
	private final AccountRepository repository;

	@Autowired
	public AccountController(AccountRepository repository) {
		this.repository = repository;
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
	

}
