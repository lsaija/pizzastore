package it.prova.pizzastore.repository.pizza;

import java.util.List;

import it.prova.pizzastore.model.Pizza;


public interface CustomPizzaRepository {
	
	List<Pizza> findByExample(Pizza example);

}
