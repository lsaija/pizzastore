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
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Proprietario", Ruolo.ROLE_PROPRIETARIO));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino", Ruolo.ROLE_FATTORINO));
		}
		
		/*
		 *     UTENTI
		 *             */
		
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.setEmail("a.admin@prova.it");
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("Pizzaiolo") == null) {
			Utente pizzaiolo = new Utente("Pizzaiolo", "Pizzaiolo", "Antonio", "Verdi", new Date());
			pizzaiolo.setEmail("p.pizzaiolo@prova.it");
			pizzaiolo.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
			utenteServiceInstance.inserisciNuovo(pizzaiolo);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(pizzaiolo.getId());
		}
		
		if (utenteServiceInstance.findByUsername("Proprietario") == null) {
			Utente proprietario = new Utente("Proprietario", "Proprietario", "Antonio", "Verdi", new Date());
			proprietario.setEmail("p.proprietario@prova.it");
			proprietario.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO));
			utenteServiceInstance.inserisciNuovo(proprietario);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(proprietario.getId());
		}
		
		if (utenteServiceInstance.findByUsername("Fattorino") == null) {
			Utente fattorino = new Utente("Fattorino", "Fattorino", "Giacomo", "Bianco", new Date());
			fattorino.setEmail("f.fattorino@prova.it");
			fattorino.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO));
			utenteServiceInstance.inserisciNuovo(fattorino);
			// l'inserimento avviene come created ma io voglio attivarlo
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
		Cliente clienteAttivissimo = new Cliente("Magalli", "Brosio", "via brutta 6", true);
		Cliente clienteNonAttivo = new Cliente("Son", "goku", "via giappone 77", false);
		clienteServiceInstance.inserisciNuovo(clienteAttivissimo);
		clienteServiceInstance.inserisciNuovo(clienteAttivo);
		clienteServiceInstance.inserisciNuovo(clienteNonAttivo);
		
		/*
		 *   ORDINI
		 *            */
		
		Ordine ordine = new Ordine("A74673", LocalDate.now(), 128, false, clienteAttivo);
		ordine.getListaPizze().add(margherita);
		ordine.getListaPizze().add(pistacchiosa);
		ordine.setFattorino(utenteServiceInstance.findByUsername("Fattorino"));
		ordineServiceInstance.inserisciNuovo(ordine);
		
		Ordine ordine2 = new Ordine("B7362", LocalDate.now(), 13, false, clienteAttivissimo);
		ordine2.getListaPizze().add(margherita);
		ordine2.getListaPizze().add(pistacchiosa);
		ordine2.setFattorino(utenteServiceInstance.findByUsername("Fattorino"));
		ordineServiceInstance.inserisciNuovo(ordine2);
	}
		
	

}
