package it.prova.pizzastore.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastore.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
