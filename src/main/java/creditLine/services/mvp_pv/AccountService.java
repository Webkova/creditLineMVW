package creditLine.services.mvp_pv;

import creditLine.persistence.entities.Account;


public interface AccountService {
	
	Account save();
	
	void saveAccount(); 
	
	void getAccountByIdclient(int idclient);

	void deleteByIdaccount(int idaccount);
	
	void deleteAccount();
	
	void updateAccount();

	Account findByIdaccount(int idaccount);

	void addAccount();

	void refreshAccount();

	void displayAccountSelected();

	void clearAccountFields();

	void setAccountAttributes();
	


}
