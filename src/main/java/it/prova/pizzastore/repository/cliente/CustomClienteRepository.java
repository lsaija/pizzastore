package it.prova.pizzastore.repository.cliente;

import java.util.List;

import it.prova.pizzastore.model.Cliente;

public interface CustomClienteRepository {
	
	List<Cliente> findByExample(Cliente example);


}
