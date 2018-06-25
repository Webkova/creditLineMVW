package creditLine.services.mvp_pv;

import java.util.List;

import creditLine.persistence.entities.Account;

public interface AccountService {
	
	Account save(Account account);
	
	void saveAccount(); 
	
	List<Account> getAccountByIdclient(int idclient);

	void updateAccount(int idaccount, String concept, int accountType, int accountStatus);

	void deleteByIdaccount(int idaccount);
	
	void deleteAccount();
	
	void showAccountSelected();
	
	void showAccounts(int idSelected);
	
	void updateAccount();
	


}
