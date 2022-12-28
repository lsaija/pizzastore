package it.prova.pizzastore.repository.ordine;

import java.util.List;

import it.prova.pizzastore.model.Ordine;

public interface CustomOrdineRepository {
	
	List<Ordine> findByExample(Ordine example);

}
