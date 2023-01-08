package it.prova.pizzastore.service.ordine;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.dto.statistiche.StatisticheDTO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.repository.ordine.OrdineRepository;
import it.prova.pizzastore.repository.utente.UtenteRepository;
import it.prova.pizzastore.web.api.exception.UtenteNotFoundException;

@Service
@Transactional
public class OrdineServiceImpl implements OrdineService{

	@Autowired
	private OrdineRepository ordineRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public List<Ordine> listAllOrdini() {
		return (List<Ordine>) ordineRepository.findAll();
		
	}
	
	public List<Ordine> listAllElementsSingoloCliente(Long id) {
		return ordineRepository.findAllOrdineByCliente(id);
	}


	@Override
	public Ordine caricaSingoloOrdine(Long id) {
	   return ordineRepository.findById(id).orElse(null);
	}

	@Override
	public Ordine caricaSingoloOrdineEager(Long id) {
		return ordineRepository.findByIdEager(id).orElse(null);
	}

	@Override
	public void aggiorna(Ordine ordineInstance) {
		ordineRepository.save(ordineInstance);
		
	}

	@Override
	public void inserisciNuovo(Ordine ordineInstance) {
		ordineInstance.setClosed(false);
		ordineRepository.save(ordineInstance);
		ordineInstance.setCostoTotale(calcolaPrezzoOrdineTotale(ordineInstance.getCodice()));
		ordineRepository.save(ordineInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		Ordine ordineInstance= ordineRepository.findById(idToRemove).orElse(null);
		
		ordineInstance.setClosed(true);
		
		ordineRepository.save(ordineInstance);
		
	}

	@Override
	public List<Ordine> findByExample(Ordine example) {
		return ordineRepository.findByExample(example);
	}

	@Override
	public Integer calcolaPrezzoOrdineTotale(String codice) {
		return ordineRepository.calcolaPrezzoOrdine(codice);
	}

	@Override
	public List<Ordine> listAllElementsWithNotClosed() {
		return ordineRepository.findAllOrdineByClosed();
	}

	@Override
	public Integer calcolaRicaviOrdiniIntervalloDate(LocalDate dataInizio, LocalDate dataFine) {
		return ordineRepository.calcolaRicaviOrdiniIntervallo(dataInizio, dataFine);
	}

	@Override
	public Integer calcolaNumeroPizzeOrdinate(LocalDate dataInizio, LocalDate dataFine) {
		return ordineRepository.calcolaNumeroPizze(dataInizio,dataFine);
	}

	@Override
	public Integer calcolaNumeroOrdiniIntervallo(LocalDate dataInizio, LocalDate dataFine) {
		return ordineRepository.countByDataBetween(dataInizio,dataFine);
	}

	@Override
	public List<Ordine> cercaOrdiniTraDateDiClienteConPizze(Long clienteId, Long pizzaId, StatisticheDTO example) {
		return ordineRepository.findOrdineTraDateDiClienteConPizza(clienteId, pizzaId, example.getDataInizio(),example.getDataFine());
	}

	@Override
	public List<Ordine> findAllOrdineByClosedAndFattorinoLog(String usernameFattorino) {
		Utente fattorino = utenteRepository.findByUsername(usernameFattorino).orElse(null);
		if (fattorino == null)
			throw new UtenteNotFoundException("Utente Not Found con username: " + usernameFattorino);
		return ordineRepository.findAllOrdineByClosedAndFattorino(fattorino.getId());
	}

	@Override
	public Ordine caricaOrdineEagerCodice(String codice) {
		return ordineRepository.findByCodiceEager(codice).orElse(null);
	}


		
		
	

}
