package it.prova.pizzastore.repository.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.prova.pizzastore.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>, CustomClienteRepository {

	Cliente findByNomeAndCognome(String nome, String cognome);

	List<Cliente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome,
			String nome);

	@Query("select distinct r from Cliente r left join fetch r.ordini ")
	List<Cliente> findAllEager();

	@Query("from Cliente r left join fetch r.ordini where r.id=?1")
	Cliente findByIdEager(Long idCliente);

}
