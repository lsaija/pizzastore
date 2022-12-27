package it.prova.pizzastore.dto.cliente;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.pizzastore.dto.ordine.OrdineDTO;
import it.prova.pizzastore.model.Cliente;

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

	@JsonIgnoreProperties(value = { "cliente" })
	private Set<OrdineDTO> ordini = new HashSet<OrdineDTO>(0);

	public ClienteDTO() {
	}

	public ClienteDTO(Long id, String nome, String cognome, String indirizzo, boolean attivo, Set<OrdineDTO> ordini) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.attivo = attivo;
		this.ordini = ordini;
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

	public Set<OrdineDTO> getOrdini() {
		return ordini;
	}

	public void setOrdini(Set<OrdineDTO> ordini) {
		this.ordini = ordini;
	}

	public Cliente buildClienteModel() {
		return new Cliente(this.id, this.nome, this.cognome, this.indirizzo, this.attivo);

	}

	public static ClienteDTO buildClienteDTOFromModel(Cliente clienteModel, boolean includeOrdini) {
		ClienteDTO result = new ClienteDTO(clienteModel.getId(), clienteModel.getNome(), clienteModel.getCognome(),
				clienteModel.getIndirizzo(), clienteModel.isAttivo());

		if (includeOrdini)
			result.setOrdini(OrdineDTO.createOrdineDTOSetFromModelSet(clienteModel.getOrdini(), false,false,false));

		return result;
	}

	public static List<ClienteDTO> createClienteDTOListFromModelList(List<Cliente> modelListInput,
			boolean includeOrdini) {
		return modelListInput.stream().map(clienteEntity -> {
			ClienteDTO result = ClienteDTO.buildClienteDTOFromModel(clienteEntity, includeOrdini);
			if (includeOrdini)
				result.setOrdini(OrdineDTO.createOrdineDTOSetFromModelSet(clienteEntity.getOrdini(), false,false,false));
			return result;
		}).collect(Collectors.toList());
	}

	public static Set<ClienteDTO> createClienteDTOSetFromModelSet(Set<Cliente> modelListInput, boolean includeOrdini) {
		return modelListInput.stream().map(clienteEntity -> {
			return ClienteDTO.buildClienteDTOFromModel(clienteEntity, includeOrdini);
		}).collect(Collectors.toSet());
	}

}
