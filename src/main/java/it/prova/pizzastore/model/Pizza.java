package it.prova.pizzastore.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pizza")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "ingredienti")
	private String ingredienti;
	
	@Column(name = "prezzo")
	private Integer prezzo;
	
	@Column(name = "attivo")
	private boolean attivo;

	
	public Pizza() {
	}


	public Pizza(String descrizione, String ingredienti, Integer prezzo, boolean attivo) {
		super();
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

	

}
