package it.prova.pizzastore.dto.pizza;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.pizzastore.model.Pizza;

public class PizzaDTO {

	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String descrizione;

	@NotBlank(message = "{ingredienti.notblank}")
	private String ingredienti;

	@NotNull(message = "{prezzo.notnull}")
	private Integer prezzo;

	@NotNull(message = "{attivo.notnull}")
	private boolean attivo;

	public PizzaDTO() {
	}

	public PizzaDTO(String descrizione, String ingredienti, Integer prezzo, boolean attivo) {
		super();
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
		this.prezzo = prezzo;
		this.attivo = attivo;
	}

	public PizzaDTO(Long id, String descrizione, String ingredienti, Integer prezzo, boolean attivo) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.ingredienti = ingredienti;
		this.prezzo = prezzo;
		this.attivo = attivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(String ingredienti) {
		this.ingredienti = ingredienti;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public Pizza buildPizzaModel() {
		Pizza result = new Pizza(this.id, this.descrizione, this.ingredienti, this.prezzo, this.attivo);
		return result;
	}

	public static PizzaDTO buildPizzaDTOFromModel(Pizza pizzaModel) {
		PizzaDTO result = new PizzaDTO(pizzaModel.getId(), pizzaModel.getDescrizione(), pizzaModel.getIngredienti(),
				pizzaModel.getPrezzo(), pizzaModel.isAttivo());
		return result;
	}

	public static List<PizzaDTO> createPizzaDTOListFromModelList(List<Pizza> modelListInput) {
		return modelListInput.stream().map(pizzaEntity -> {
			return PizzaDTO.buildPizzaDTOFromModel(pizzaEntity);
		}).collect(Collectors.toList());
	}

	public static Set<PizzaDTO> createPizzaDTOSetFromModelSet(Set<Pizza> modelListInput) {
		return modelListInput.stream().map(pizzaEntity -> {
			return PizzaDTO.buildPizzaDTOFromModel(pizzaEntity);
		}).collect(Collectors.toSet());
	}
}
