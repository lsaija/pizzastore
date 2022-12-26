package it.prova.pizzastore.dto.cliente;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;

public class ClienteDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;

	@NotNull(message = "{attivo.notnull}")
	private boolean attivo;

	private Long[] ordiniId;

	public ClienteDTO() {
	}

	public ClienteDTO(Long id, String nome, String cognome, String indirizzo, boolean attivo, Long[] ordiniId) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.attivo = attivo;
		this.ordiniId = ordiniId;
	}

	public ClienteDTO(String nome, String cognome, String indirizzo, boolean attivo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.attivo = attivo;
	}

	public ClienteDTO(Long id, String nome, String cognome, String indirizzo, boolean attivo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.attivo = attivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public Long[] getOrdiniId() {
		return ordiniId;
	}

	public void setOrdiniId(Long[] ordiniId) {
		this.ordiniId = ordiniId;
	}

	public Cliente buildClienteModel(boolean includeIdOrdini) {
		Cliente result = new Cliente(this.id, this.nome, this.cognome, this.indirizzo, this.attivo);
		if (includeIdOrdini && ordiniId != null)
			result.setOrdini(Arrays.asList(ordiniId).stream().map(id -> new Ordine(id)).collect(Collectors.toSet()));

		return result;
	}

	public static ClienteDTO buildClienteDTOFromModel(Cliente clienteModel, boolean includeIdOrdini) {
		ClienteDTO result = new ClienteDTO(clienteModel.getId(), clienteModel.getNome(), clienteModel.getCognome(),
				clienteModel.getIndirizzo(), clienteModel.isAttivo());

		if (!clienteModel.getOrdini().isEmpty() && includeIdOrdini)
			result.ordiniId = clienteModel.getOrdini().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}

	public static List<ClienteDTO> createClienteDTOListFromModelList(List<Cliente> modelListInput,
			boolean includeIdOrdini) {
		return modelListInput.stream().map(clienteEntity -> {
			return ClienteDTO.buildClienteDTOFromModel(clienteEntity, includeIdOrdini);
		}).collect(Collectors.toList());
	}

	public static Set<ClienteDTO> createClienteDTOSetFromModelSet(Set<Cliente> modelListInput,
			boolean includeIdOrdini) {
		return modelListInput.stream().map(clienteEntity -> {
			return ClienteDTO.buildClienteDTOFromModel(clienteEntity, includeIdOrdini);
		}).collect(Collectors.toSet());
	}

}
