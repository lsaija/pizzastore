package it.prova.pizzastore.repository.pizza;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastore.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long>,CustomPizzaRepository {



}
