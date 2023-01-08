package it.prova.pizzastore.service.ordine;

import java.time.LocalDate;
import java.util.List;

import it.prova.pizzastore.dto.statistiche.StatisticheDTO;
import it.prova.pizzastore.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAllOrdini();
	
	public List<Ordine> listAllElementsSingoloCliente(Long id);

	public Ordine caricaSingoloOrdine(Long id);

	public Ordine caricaSingoloOrdineEager(Long id);
	
	public Ordine caricaOrdineEagerCodice(String codice);

	public void aggiorna(Ordine ordineInstance);

	public void inserisciNuovo(Ordine ordineInstance);

	public void rimuovi(Long idToRemove);

	public List<Ordine> findByExample(Ordine example);
	
	public Integer calcolaPrezzoOrdineTotale(String codice);
	
	public List<Ordine> cercaOrdiniTraDateDiClienteConPizze(Long clienteId,Long pizzaId,StatisticheDTO example);
	
	public List<Ordine> listAllElementsWithNotClosed();
	
	public Integer calcolaRicaviOrdiniIntervalloDate(LocalDate dataInizio, LocalDate dataFine);
	
	public Integer calcolaNumeroPizzeOrdinate(LocalDate dataInizio, LocalDate dataFine);
	
	public Integer calcolaNumeroOrdiniIntervallo(LocalDate dataInizio, LocalDate dataFine);
	
	List<Ordine> findAllOrdineByClosedAndFattorinoLog(String usernameFattorino);

}
