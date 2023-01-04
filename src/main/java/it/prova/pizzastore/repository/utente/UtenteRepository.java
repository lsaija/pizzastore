package it.prova.pizzastore.repository.utente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastore.model.StatoUtente;
import it.prova.pizzastore.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
	
	@EntityGraph(attributePaths = { "ruoli" })
	Optional<Utente> findByUsername(String username);
	
	@Query("from Utente u left join fetch u.ruoli where u.id = ?1")
	Optional<Utente> findByIdConRuoli(Long id);
	
	Utente findByUsernameAndPassword(String username, String password);
	
	//caricamento eager, ovviamente si può fare anche con jpql
	@EntityGraph(attributePaths = { "ruoli" })
	Utente findByUsernameAndPasswordAndStato(String username,String password, StatoUtente stato);
	
	@Query("from Utente u join fetch u.ruoli r where r.descrizione = 'ROLE_FATTORINO'")
	List<Utente> findAllFattorini();
	
	
}
