package creditLine.services.mvp_pm;

import java.util.List;

import creditLine.persistence.entities.Account;

public interface AccountService {
	
	Account save(Account account);
	
	List<Account> getAccountByIdclient(int idclient);

	void updateAccount(int idaccount, String concept, int accountType, int accountStatus);

	void deleteByIdaccount(int idaccount);

}
