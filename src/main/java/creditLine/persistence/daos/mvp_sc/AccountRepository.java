package creditLine.persistence.daos.mvp_sc;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import creditLine.persistence.entities.Account;

@Transactional()
public interface AccountRepository extends CrudRepository<Account, Long> {

	@Query("SELECT account FROM Client c WHERE c.idclient = ?1")
	List<Account> getAccountByIdclient(int idclient);
	
	@Modifying
	@Query("UPDATE Account c SET c.concept = ?2, c.accountType = ?3, c.accountStatus = ?4 WHERE idaccount = ?1")
	void updateAccount(int idaccount,String concept, int accountType, int accountStatus);
	
	int deleteByIdaccount(int idaccount);

	
}
