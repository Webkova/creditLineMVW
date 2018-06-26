package creditLine.persistence.daos.mvp_sc;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import creditLine.persistence.entities.Client;

import java.util.List;


@Transactional()
public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findAll();
    
	@Modifying
	@Query("UPDATE Client c SET c.name = ?2, c.surname = ?3, c.address = ?4, c.nationality = ?5 WHERE idclient = ?1")
	void updateClient(int idclient,String name, String surname, String address, String nationality);
	
	int deleteByIdclient(int idclient);
	

}


