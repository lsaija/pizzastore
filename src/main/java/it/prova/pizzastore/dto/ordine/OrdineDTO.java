package it.prova.pizzastore.dto.ordine;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastore.dto.cliente.ClienteDTO;
import it.prova.pizzastore.dto.pizza.PizzaDTO;
import it.prova.pizzastore.dto.utente.UtenteDTO;
import it.prova.pizzastore.model.Ordine;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdineDTO {

	private Long id;

	@NotNull(message = "{data.notnull}")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;

	@NotNull(message = "{closed.notnull}")
	private boolean closed;

	@NotBlank(message = "{codice.notblank}")
	private String codice;

	private Integer costoTotale;

	@NotNull(message = "{cliente.notnull}")
	@JsonIgnoreProperties(value = { "ordini" })
	private ClienteDTO cliente;

	private UtenteDTO fattorino;

	private List<PizzaDTO> pizze;

	public OrdineDTO() {
	}

	public OrdineDTO(Long id, LocalDate data, boolean closed, String codice, Integer costoTotale, ClienteDTO cliente,
			UtenteDTO fattorino,List<PizzaDTO> pizze) {
		super();
		this.id = id;
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.cliente = cliente;
		this.fattorino = fattorino;
		this.pizze = pizze;
	}

	public OrdineDTO(LocalDate data, boolean closed, String codice, Integer costoTotale, ClienteDTO cliente) {
		super();
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.cliente = cliente;
	}

	public OrdineDTO(Long id, LocalDate data, boolean closed, String codice, Integer costoTotale) {
		super();
		this.id = id;
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotale = costoTotale;

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

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public UtenteDTO getFattorino() {
		return fattorino;
	}

	public void setFattorino(UtenteDTO fattorino) {
		this.fattorino = fattorino;
	}

	public List<PizzaDTO> getPizze() {
		return pizze;
	}

	public void setPizze(List<PizzaDTO> pizze) {
		this.pizze = pizze;
	}

	public Ordine buildOrdineModel() {
		Ordine result = new Ordine(this.id, this.data, this.closed, this.codice, this.costoTotale);

		if (this.cliente != null)
			result.setCliente(this.cliente.buildClienteModel());

		if (this.fattorino != null)
			result.setFattorino(this.fattorino.buildUtenteModel(true));
		
		if (this.pizze != null)
			result.setListaPizze(PizzaDTO.createPizzaModelSetFromDTOList(this.pizze));

		return result;
	}

	public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel, boolean includePizze, boolean includeCliente,
			boolean includeFattorino) {
		OrdineDTO result = new OrdineDTO(ordineModel.getId(), ordineModel.getData(), ordineModel.getClosed(),
				ordineModel.getCodice(), ordineModel.getCostoTotale());

		if (includeCliente)
			result.setCliente(ClienteDTO.buildClienteDTOFromModel(ordineModel.getCliente(), false));

		if (includeFattorino)
			result.setFattorino(UtenteDTO.buildUtenteDTOFromModel(ordineModel.getFattorino()));

		if (includePizze)
			result.setPizze(PizzaDTO.createPizzaDTOListFromModelSet(ordineModel.getListaPizze()));

		return result;
	}

	public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput, boolean includePizze,
			boolean includeCliente, boolean includeFattorino) {
		return modelListInput.stream().map(ordineEntity -> {
			OrdineDTO result = OrdineDTO.buildOrdineDTOFromModel(ordineEntity, includePizze, includeCliente,
					includeFattorino);
			if (includePizze)
				result.setPizze(PizzaDTO.createPizzaDTOListFromModelSet(ordineEntity.getListaPizze()));
			return result;
		}).collect(Collectors.toList());
	}

	public static Set<OrdineDTO> createOrdineDTOSetFromModelSet(Set<Ordine> modelListInput, boolean includePizze,
			boolean includeCliente, boolean includeFattorino) {
		return modelListInput.stream().map(ordineEntity -> {
			return OrdineDTO.buildOrdineDTOFromModel(ordineEntity, includePizze, includeCliente, includeFattorino);
		}).collect(Collectors.toSet());
	}

}
