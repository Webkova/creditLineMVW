package creditLine.persistence.daos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import creditLine.persistence.entities.Client;

import java.util.List;


@Transactional(propagation = Propagation.MANDATORY)
public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findAll();
    
	@Modifying
	@Query("UPDATE Client c SET c.name = ?2 WHERE idclient = ?1")
	void updateClient(int idclient,String name);
	
	int deleteByIdclient(int idclient);

}


