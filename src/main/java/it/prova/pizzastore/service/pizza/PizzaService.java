package it.prova.pizzastore.service.pizza;

import java.util.List;

import it.prova.pizzastore.model.Pizza;

public interface PizzaService {

	public List<Pizza> listAllPizze();

	public Pizza caricaSingolaPizza(Long id);

	public void aggiorna(Pizza pizzaInput);

	public void inserisciNuovo(Pizza pizzaInput);

	public void rimuovi(Long idToRemove);

	public List<Pizza> findByExample(Pizza example);
	

}
