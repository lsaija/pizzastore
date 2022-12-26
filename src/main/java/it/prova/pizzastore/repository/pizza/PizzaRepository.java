package it.prova.pizzastore.repository.pizza;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastore.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {

	List<Pizza> findByIngredienti(String ingredienti);

	List<Pizza> findByPrezzo(Integer prezzo);

}
