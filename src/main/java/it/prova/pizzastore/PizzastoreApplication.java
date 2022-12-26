package it.prova.pizzastore;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.cliente.ClienteService;
import it.prova.pizzastore.service.ordine.OrdineService;
import it.prova.pizzastore.service.pizza.PizzaService;
import it.prova.pizzastore.service.ruolo.RuoloService;
import it.prova.pizzastore.service.utente.UtenteService;

@SpringBootApplication
public class PizzastoreApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private PizzaService pizzaServiceInstance;
	@Autowired
	private ClienteService clienteServiceInstance;
	@Autowired
	private OrdineService ordineServiceInstance;
	
	public static void main(String[] args) {
		SpringApplication.run(PizzastoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 *    RUOLI    
		 *           */
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ADMIN_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ADMIN_ROLE));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.PIZZAIOLO_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo", Ruolo.PIZZAIOLO_ROLE));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.PROPRIETARIO_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Proprietario", Ruolo.PROPRIETARIO_ROLE));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.FATTORINO_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino", Ruolo.FATTORINO_ROLE));
		}
		
		/*
		 *     UTENTI
		 *             */
		
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ADMIN_ROLE));
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}
		
		if (utenteServiceInstance.findByUsername("pizzaiolo") == null) {
			Utente pizzaiolo = new Utente("pizzaiolo", "pizzaiolo", "Rocco", "figo", new Date());
			pizzaiolo.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.PIZZAIOLO_ROLE));
			utenteServiceInstance.inserisciNuovo(pizzaiolo);
			utenteServiceInstance.changeUserAbilitation(pizzaiolo.getId());
		}
		
		if (utenteServiceInstance.findByUsername("proprietario") == null) {
			Utente proprietario = new Utente("proprietario", "proprietario", "Tiziana", "boss", new Date());
			proprietario.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.PROPRIETARIO_ROLE));
			utenteServiceInstance.inserisciNuovo(proprietario);
			utenteServiceInstance.changeUserAbilitation(proprietario.getId());
		}
		
		if (utenteServiceInstance.findByUsername("fattorino") == null) {
			Utente fattorino = new Utente("fattorino", "fattorino", "Pecorino", "Provola", new Date());
			fattorino.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.FATTORINO_ROLE));
			utenteServiceInstance.inserisciNuovo(fattorino);
			utenteServiceInstance.changeUserAbilitation(fattorino.getId());
		}
		
		/*
		 *  PIZZE
		 *          */
		
		Pizza margherita = new Pizza("margherita", "pomodoro e mozzarella", 5, true);
		Pizza calabrese = new Pizza("calabrese", "schiacciata,pomodoro e mozzarella", 6, true);
		Pizza nduja = new Pizza("nduja", "pomodoro, mozzarella e nduja", 7, false);
		Pizza pistacchiosa = new Pizza("pistacchiosa", "mozzarella,mortadella e pistacchio", 8, true);
		pizzaServiceInstance.inserisciNuovo(margherita);
		pizzaServiceInstance.inserisciNuovo(calabrese);
		pizzaServiceInstance.inserisciNuovo(nduja);
		pizzaServiceInstance.inserisciNuovo(pistacchiosa);
		
		/*
		 *  CLIENTI
		 *           */
		
		Cliente clienteAttivo = new Cliente("Peppe", "Pepinho", "via bella 5", true);
		Cliente clienteNonAttivo = new Cliente("Magalli", "Brosio", "via brutta 6", false);
		clienteServiceInstance.inserisciNuovo(clienteNonAttivo);
		clienteServiceInstance.inserisciNuovo(clienteAttivo);
		
		/*
		 *   ORDINI
		 *            */
		
		Ordine ordine = new Ordine("A74673", LocalDate.now(), 13, false, clienteAttivo);
		ordine.getListaPizze().add(margherita);
		ordine.getListaPizze().add(pistacchiosa);
		ordine.setFattorino(utenteServiceInstance.findByUsername("fattorino"));
		ordineServiceInstance.inserisciNuovo(ordine);
	}
		
	

}
