package it.prova.pizzastore.service.ordine;

import java.time.LocalDate;
import java.util.List;

import it.prova.pizzastore.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAllOrdini();
	
	public List<Ordine> listAllElementsSingoloCliente(Long id);

	public Ordine caricaSingoloOrdine(Long id);

	public Ordine caricaSingoloOrdineEager(Long id);

	public void aggiorna(Ordine ordineInstance);

	public void inserisciNuovo(Ordine ordineInstance);

	public void rimuovi(Long idToRemove);

	public List<Ordine> findByExample(Ordine example);
	
	public Integer calcolaPrezzoOrdineTotale(Long id);
	
	public List<Ordine> cercaOrdiniTraDateDiClienteConPizze(Long clienteId,Long pizzaId,LocalDate inizio,LocalDate fine);
	
	public List<Ordine> listAllElementsWithNotClosed();
	
	public Integer calcolaRicaviOrdiniIntervalloDate(LocalDate inizio,LocalDate fine);
	
	public Integer calcolaNumeroPizzeOrdinate(LocalDate inizio,LocalDate fine);
	
	public Integer calcolaNumeroOrdiniIntervallo(LocalDate inizio,LocalDate fine);
	
	List<Ordine> findAllOrdineByClosedAndFattorinoLog(String usernameLogged);

}
