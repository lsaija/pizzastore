package it.prova.pizzastore.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.repository.cliente.ClienteRepository;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	
	@Override
	public List<Cliente> listAllClienti() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Cliente caricaSingoloCliente(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	@Override
	public Cliente caricaSingoloClienteConOrdini(Long id) {
		return clienteRepository.findByIdEager(id);
	}

	@Override
	@Transactional
	public void aggiorna(Cliente clienteInstance) {
		clienteRepository.save(clienteInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Cliente clienteInstance) {
		clienteRepository.save(clienteInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		Cliente clienteInstance = clienteRepository.findById(idToRemove).orElse(null);

		clienteInstance.setAttivo(false);

		clienteRepository.save(clienteInstance);
	}

	@Override
	public List<Cliente> findByExample(Cliente example) {
		return clienteRepository.findByExample(example);
	}

	@Override
	public List<Cliente> listAllClientiVirtuosi() {
		return clienteRepository.findAllClientiVirtuosi();
	}

}
