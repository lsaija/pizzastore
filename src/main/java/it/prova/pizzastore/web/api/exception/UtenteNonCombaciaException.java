package it.prova.pizzastore.web.api.exception;

public class UtenteNonCombaciaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UtenteNonCombaciaException(String message) {
		super(message);
	}

}
