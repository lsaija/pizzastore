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
	
	@Query("select t from Ordine t left join fetch t.cliente u where u.id=?1")
	List<Ordine> findAllOrdineByCliente(Long id);
	
	@Query("select sum(p.prezzo) from Ordine o join  o.listaPizze p where o.id=?1 ")
	Integer calcolaPrezzoOrdine(Long id);

	@Query("select t from Ordine t left join fetch t.cliente u where t.closed=false")
	List<Ordine> findAllOrdineByClosed();
	
	/*  
	 *  DA TESTARE
	 *              */
	
	@Query("select o from Ordine o left join fetch o.listaPizze p left join fetch o.cliente c left join fetch o.fattorino where c.id = ?1 and p.id =?2 and o.data between ?3 and ?4")
	List<Ordine> findOrdineTraDateDiClienteConPizza(Long clienteId,Long pizzaId,LocalDate inizio,LocalDate fine);
	
	@Query("select sum(o.costoTotale) from Ordine o where  o.data between ?1 and ?2 ")
	Integer calcolaRicaviOrdiniIntervallo(LocalDate inizio,LocalDate fine);
	
	@Query("select count(o.listaPizze) from Ordine o join o.listaPizze where o.data>?1 and o.data<?2 ")
	Integer calcolaNumeroPizze(LocalDate inizio,LocalDate fine);
	
	@Query("select count(*) from Ordine o where o.data between ?1 and ?2 ")
	Integer calcolaNumeroOrdini(LocalDate inizio,LocalDate fine);
	
	@Query("select t from Ordine t left join fetch t.cliente c left join fetch t.fattorino f where t.closed=false and f.username=?1")
	List<Ordine> findAllOrdineByClosedAndFattorino(String username);
}
