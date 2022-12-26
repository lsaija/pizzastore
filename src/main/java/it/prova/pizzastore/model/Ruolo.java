package it.prova.pizzastore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ruolo")
public class Ruolo {
	
	public static final String ADMIN_ROLE = "ADMIN_ROLE,";
	public static final String PIZZAIOLO_ROLE = "PIZZAIOLO_ROLE";
	public static final String PROPRIETARIO_ROLE = "PROPRIETARIO_ROLE,";
	public static final String FATTORINO_ROLE = "FATTORINO_ROLE";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "codice")
	private String codice;

	public Ruolo() {
	}
	
	public Ruolo(String descrizione, String codice) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
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

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	//---------------------
	public static String getAdminRole() {
		return ADMIN_ROLE;
	}

	public static String getPizzaioloRole() {
		return PIZZAIOLO_ROLE;
	}

	public static String getProprietarioRole() {
		return PROPRIETARIO_ROLE;
	}

	public static String getFattorinoRole() {
		return FATTORINO_ROLE;
	}
	
	

}
