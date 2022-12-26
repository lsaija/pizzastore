package it.prova.pizzastore.repository.ordine;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastore.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long> {

	@Query("from Ordine o left join fetch o.listaPizze left join fetch o.cliente left join fetch o.fattorino where o.id = ?1")
	Optional<Ordine> findByIdEager(Long id);

}
