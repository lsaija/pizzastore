package it.prova.pizzastore.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordine")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "data")
	private LocalDate data;

	@Column(name = "closed")
	private boolean closed;

	@Column(name = "codice")
	private String codice;

	@Column(name = "costoTotale")
	private Integer costoTotale;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente fattorino;

	@ManyToMany
	@JoinTable(name = "ordine_pizza", joinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"))
	private Set<Pizza> listaPizze = new HashSet<>(0);

	public Ordine() {
	}

	public Ordine(Long id) {
		super();
		this.id = id;
	}

	public Ordine(Long id, LocalDate data, boolean closed, String codice, Integer costoTotale, Cliente cliente,
			Utente fattorino, Set<Pizza> listaPizze) {
		super();
		this.id = id;
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.cliente = cliente;
		this.fattorino = fattorino;
		this.listaPizze = listaPizze;
	}

	public Ordine(LocalDate data, boolean closed, String codice, Integer costoTotale, Cliente cliente) {
		super();
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.cliente = cliente;
	}

	public Ordine(LocalDate data, boolean closed, String codice, Integer costoTotale, Cliente cliente,
			Set<Pizza> listaPizze) {
		super();
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.cliente = cliente;
		this.listaPizze = listaPizze;
	}

	public Ordine(String codice, LocalDate data, Integer costoTotale, boolean closed, Cliente cliente) {
		this.codice = codice;
		this.data = data;
		this.costoTotale = costoTotale;
		this.closed = closed;
		this.cliente = cliente;
	}

	public Ordine(Long id, LocalDate data, boolean closed, String codice, Integer costoTotale) {
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

	public Set<Pizza> getListaPizze() {
		return listaPizze;
	}

	public void setListaPizze(Set<Pizza> listaPizze) {
		this.listaPizze = listaPizze;
	}

}
