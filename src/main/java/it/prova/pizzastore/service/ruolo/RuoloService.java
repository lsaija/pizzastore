package it.prova.pizzastore.service.ruolo;

import java.util.List;

import it.prova.pizzastore.model.Ruolo;


public interface RuoloService {
	
	public List<Ruolo> listAll();

	public Ruolo caricaSingoloElemento(Long id);

	public void aggiorna(Ruolo ruoloInput);

	public void inserisciNuovo(Ruolo ruoloInput);

	public void rimuovi(Long idToRemove);

	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice);
	
	public Ruolo cercePerCodice(String codiceInput);

}
