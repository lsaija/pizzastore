package it.prova.pizzastore.service.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.repository.pizza.PizzaRepository;

public class PizzaServiceImpl implements PizzaService{

	@Autowired
	PizzaRepository pizzaRepository;
	
	@Override
	public List<Pizza> listAllPizze() {
		return (List<Pizza>) pizzaRepository.findAll(); 
	}

	@Override
	public Pizza caricaSingolaPizza(Long id) {
		return pizzaRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Pizza pizzaInput) {
		pizzaRepository.save(pizzaInput);
		
	}

	@Override
	public void inserisciNuovo(Pizza pizzaInput) {
		pizzaRepository.save(pizzaInput);
		
	}

	@Override
	public void rimuovi(Long idToRemove) {
		Pizza pizzaInstance=pizzaRepository.findById(idToRemove).orElse(null);
		pizzaInstance.setAttivo(false);
		pizzaRepository.save(pizzaInstance);
		
	}

	@Override
	public List<Pizza> findByExample(Pizza example) {
		// TODO Auto-generated method stub
		return null;
	}



}
