package creditLine.services.mvp_pv;

import creditLine.persistence.entities.Account;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface AccountService {
	
	Account save();
	
	void saveAccount(); 
	
	void getAccountByIdclient(int idclient);

	void deleteByIdaccount(int idaccount);
	
	void deleteAccount();
	
	void updateAccount();

	Account findByIdaccount(int idaccount);

	IntegerProperty getIdaccount();

	void setIdaccount(IntegerProperty idaccount);

	StringProperty getConcept();

	void setConcept(StringProperty concept);

	IntegerProperty getAccountType();

	void setAccountType(IntegerProperty accountType);

	IntegerProperty getAccountStatus();

	void setAccountStatus(IntegerProperty accountStatus);

	StringProperty getCreationDate();

	void setCreationDate(StringProperty creationDate);

	void addAccount();

	void refreshAccount();
	


}
