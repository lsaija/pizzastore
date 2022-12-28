package it.prova.pizzastore.service.cliente;

import java.util.List;

import it.prova.pizzastore.model.Cliente;

public interface ClienteService {
	
   public List<Cliente> listAllClienti();
	
	public Cliente caricaSingoloCliente(Long id);
	
	public Cliente caricaSingoloClienteConOrdini(Long id);
	
	public void aggiorna(Cliente clienteInput);

	public void inserisciNuovo(Cliente clienteInput);

	public void rimuovi(Long idToRemove);

	public List<Cliente> findByExample(Cliente example);
	
	public List<Cliente> listAllClientiVirtuosi();

}
