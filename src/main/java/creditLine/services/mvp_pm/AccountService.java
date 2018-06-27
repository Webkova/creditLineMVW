package creditLine.services.mvp_pm;

import creditLine.persistence.entities.Account;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface AccountService {
	
	Account save();
	
	void getAccountByIdclient(int idclient);

	void deleteByIdaccount(int idaccount);
	
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
	


}
