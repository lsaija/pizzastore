package it.prova.pizzastore.web.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastore.dto.utente.UtenteDTO;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.security.dto.UtenteInfoJWTResponseDTO;
import it.prova.pizzastore.service.utente.UtenteService;
import it.prova.pizzastore.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastore.web.api.exception.UtenteNotFoundException;


@RestController
@RequestMapping("/api/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;


	@GetMapping("/testSoloAdmin")
	public String test() {
		return "OK";
	}


	@GetMapping(value = "/userInfo")
	public ResponseEntity<UtenteInfoJWTResponseDTO> getUserInfo() {

		// se sono qui significa che sono autenticato quindi devo estrarre le info dal
		// contesto
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		// estraggo le info dal principal
		Utente utenteLoggato = utenteService.findByUsername(username);
		List<String> ruoli = utenteLoggato.getRuoli().stream().map(item -> item.getCodice())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new UtenteInfoJWTResponseDTO(utenteLoggato.getNome(), utenteLoggato.getCognome(),
				utenteLoggato.getUsername(), utenteLoggato.getEmail(), ruoli));
	}
	
	
	@GetMapping
	public List<UtenteDTO> getAll() {
		return UtenteDTO.createUtenteDTOListFromModelList(utenteService.listAllUtenti(),false);
	}
	
	@GetMapping("/listaUtenti")
	public List<UtenteDTO> getAllConRuoli() {
		return UtenteDTO.createUtenteDTOListFromModelList(utenteService.listAllUtenti(),true);
	}
	
	@PostMapping
	public void createNew(@Valid @RequestBody UtenteDTO utenteInput) {
		if (utenteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
	    utenteService.inserisciNuovo(utenteInput.buildUtenteModel(true));
	}
	
	/*
	@PostMapping("/registra")
	public void registrazione(@Valid @RequestBody UtenteDTO utenteInput) {
		if (utenteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
	    utenteService.registra(utenteInput.buildUtenteModel(true));
	    
	}
	*/
	
	@GetMapping("/{id}")
	public UtenteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Utente utente = utenteService.caricaSingoloUtenteConRuoli(id);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		if (utente == null)
			throw new UtenteNotFoundException("Utente not found con id: " + id);
		//aggiungere condizione utente

		return UtenteDTO.buildUtenteDTOFromModel(utente,true);
	}
	
	@PutMapping("/{id}")
	public void update(@Valid @RequestBody UtenteDTO utenteInput, @PathVariable(required = true) Long id) {
		Utente utente = utenteService.caricaSingoloUtente(id);

		if (utente == null)
			throw new UtenteNotFoundException("Utente not found con id: " + id);

		utenteInput.setId(id);
		utenteService.aggiorna(utenteInput.buildUtenteModel(true));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		if (utenteService.caricaSingoloUtente(id) == null)
			throw new UtenteNotFoundException("Utente not found con id: " + id);
		utenteService.rimuovi(id);
	}

	@PostMapping("/search")
	public List<UtenteDTO> search(@RequestBody UtenteDTO example) {
		return UtenteDTO.createUtenteDTOListFromModelList(utenteService.findByExample(example.buildUtenteModel(false)),true);
	}
			
	@PutMapping("/cambiaStato/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changeUserAbilitation(@PathVariable(value = "id", required = true) long id) {
		utenteService.changeUserAbilitation(id);
	}
	
	@PutMapping("/disabilita/{id}")
	public void disabilita(@PathVariable(required = true) Long id) {
		Utente utente = utenteService.caricaSingoloUtente(id);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (utente == null)
			throw new UtenteNotFoundException("Utente not found con id: " + id);
		utenteService.disabilityUserAbilitation(id);
	}
}
