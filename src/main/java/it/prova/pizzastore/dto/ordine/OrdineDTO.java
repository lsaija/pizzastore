package it.prova.pizzastore.dto.ordine;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Utente;

public class OrdineDTO {

	private Long id;

	@NotNull(message = "{data.notnull}")
	private LocalDate data;

	@NotNull(message = "{closed.notnull}")
	private boolean closed;

	@NotBlank(message = "{codice.notblank}")
	private String codice;

	private Integer costoTotale;

	@NotNull(message = "{cliente.notnull}")
	private Cliente cliente;

	private Utente fattorino;

	private Long[] pizzeIds;

	public OrdineDTO() {
	}

	public OrdineDTO(Long id, LocalDate data, boolean closed, String codice, Integer costoTotale, Cliente cliente,
			Utente fattorino, Long[] pizzeIds) {
		super();
		this.id = id;
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.cliente = cliente;
		this.fattorino = fattorino;
		this.pizzeIds = pizzeIds;
	}

	public OrdineDTO(LocalDate data, boolean closed, String codice, Integer costoTotale, Cliente cliente) {
		super();
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.cliente = cliente;
	}

	public OrdineDTO(Long id, LocalDate data, boolean closed, String codice, Integer costoTotale, Cliente cliente,
			Utente fattorino) {
		super();
		this.id = id;
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.cliente = cliente;
		this.fattorino = fattorino;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Integer getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(Integer costoTotale) {
		this.costoTotale = costoTotale;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Utente getFattorino() {
		return fattorino;
	}

	public void setFattorino(Utente fattorino) {
		this.fattorino = fattorino;
	}

	public Long[] getPizzeIds() {
		return pizzeIds;
	}

	public void setPizzeIds(Long[] pizzeIds) {
		this.pizzeIds = pizzeIds;
	}

	public Ordine buildOrdineModel(boolean includeIdPizze) {
		Ordine result = new Ordine(this.id, this.data, this.closed, this.codice, this.costoTotale, this.cliente,
				this.fattorino);
		if (includeIdPizze && pizzeIds != null)
			result.setListaPizze(Arrays.asList(pizzeIds).stream().map(id -> new Pizza(id)).collect(Collectors.toSet()));

		return result;
	}

	public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel, boolean includeIdPizze) {
		OrdineDTO result = new OrdineDTO(ordineModel.getId(), ordineModel.getData(), ordineModel.isClosed(),
				ordineModel.getCodice(), ordineModel.getCostoTotale(), ordineModel.getCliente(),
				ordineModel.getFattorino());

		if (!ordineModel.getListaPizze().isEmpty() && includeIdPizze)
			result.pizzeIds = ordineModel.getListaPizze().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}

	public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput,
			boolean includeIdPizze) {
		return modelListInput.stream().map(ordineEntity -> {
			return OrdineDTO.buildOrdineDTOFromModel(ordineEntity, includeIdPizze);
		}).collect(Collectors.toList());
	}

	public static Set<OrdineDTO> createOrdineDTOSetFromModelSet(Set<Ordine> modelListInput, boolean includeIdPizze) {
		return modelListInput.stream().map(ordineEntity -> {
			return OrdineDTO.buildOrdineDTOFromModel(ordineEntity, includeIdPizze);
		}).collect(Collectors.toSet());
	}

}
