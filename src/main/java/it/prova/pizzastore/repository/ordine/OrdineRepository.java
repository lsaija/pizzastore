package it.prova.pizzastore.repository.ordine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastore.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>,CustomOrdineRepository {

	@Query("from Ordine o left join fetch o.listaPizze left join fetch o.cliente left join fetch o.fattorino where o.id = ?1")
	Optional<Ordine> findByIdEager(Long id);
	
	@Query("from Ordine o left join fetch o.listaPizze left join fetch o.cliente left join fetch o.fattorino where o.codice = ?1")
	Optional<Ordine> findByCodiceEager(String codice);
	
	@Query("select t from Ordine t left join fetch t.cliente u where u.id=?1")
	List<Ordine> findAllOrdineByCliente(Long id);
	
	@Query("select sum(p.prezzo) from Ordine o join  o.listaPizze p where o.codice=?1 ")
	Integer calcolaPrezzoOrdine(String codice);

	@Query("select t from Ordine t left join fetch t.cliente u where t.closed=false")
	List<Ordine> findAllOrdineByClosed();
	
	/*  
	 *  DA TESTARE
	 *              */
	
	@Query("select o from Ordine o left join fetch o.listaPizze p left join fetch o.cliente c left join fetch o.fattorino where c.id = ?1 and p.id =?2 and o.data between ?3 and ?4")
	List<Ordine> findOrdineTraDateDiClienteConPizza(Long clienteId,Long pizzaId,LocalDate inizio,LocalDate fine);
	
	@Query("select sum(o.costoTotale) from Ordine o where o.data between ?1 and ?2 ")
	Integer calcolaRicaviOrdiniIntervallo(LocalDate inizio,LocalDate fine);
	
	@Query(value = "select count(op.pizza_id) from ordine_pizza op join ordine o on o.id = op.ordine_id where o.data between ?1 and ?2", nativeQuery = true)
	Integer calcolaNumeroPizze(LocalDate inizio,LocalDate fine);
	
	Integer countByDataBetween(LocalDate dataInizio, LocalDate dataFine);
	
	@Query("from Ordine o join o.fattorino f where o.closed = false and f.id =?1")
	List<Ordine> findAllOrdineByClosedAndFattorino(Long idFattorino);
}
