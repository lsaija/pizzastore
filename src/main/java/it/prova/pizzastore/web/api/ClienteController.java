package it.prova.pizzastore.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastore.dto.cliente.ClienteDTO;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.cliente.ClienteService;
import it.prova.pizzastore.web.api.exception.ClienteNotFoundException;
import it.prova.pizzastore.web.api.exception.IdNotNullForInsertException;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<ClienteDTO> getAll() {
		return ClienteDTO.createClienteDTOListFromModelList(clienteService.listAllClienti(), true);
	}

	@GetMapping("/{id}")
	public ClienteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Cliente cliente = clienteService.caricaSingoloClienteConOrdini(id);

		if (cliente == null)
			throw new ClienteNotFoundException("Cliente not found con id: " + id);

		return ClienteDTO.buildClienteDTOFromModel(cliente, true);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createNew(@Valid @RequestBody ClienteDTO clienteInput) {
		if (clienteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		clienteService.inserisciNuovo(clienteInput.buildClienteModel());
	}

	@PutMapping("/{id}")
	public void update(@Valid @RequestBody ClienteDTO clienteInput, @PathVariable(required = true) Long id) {
		Cliente cliente = clienteService.caricaSingoloCliente(id);

		if (cliente == null)
			throw new ClienteNotFoundException("Cliente not found con id: " + id);

		clienteInput.setId(id);
		clienteService.aggiorna(clienteInput.buildClienteModel());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		clienteService.rimuovi(id);
	}

	@PostMapping("/search")
	public List<ClienteDTO> search(@RequestBody ClienteDTO example) {
		return ClienteDTO.createClienteDTOListFromModelList(clienteService.findByExample(example.buildClienteModel()),
				true);
	}

}
