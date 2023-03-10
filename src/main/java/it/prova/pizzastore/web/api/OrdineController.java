package it.prova.pizzastore.web.api;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastore.dto.ordine.OrdineDTO;
import it.prova.pizzastore.dto.statistiche.StatisticheDTO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.ordine.OrdineService;
import it.prova.pizzastore.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastore.web.api.exception.OrdineNotFoundException;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

	@Autowired
	private OrdineService ordineService;

	@GetMapping(value = "/ordineInfo")
	public List<OrdineDTO> getAll() {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.listAllOrdini(), true, true, true);
	}

	@GetMapping(value = "/ordiniCliente/{id}")
	public List<OrdineDTO> getAllUtenteSingolo(@PathVariable(value = "id", required = true) long id) {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.listAllElementsSingoloCliente(id), true, true,
				true);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createNew(@Valid @RequestBody OrdineDTO ordineInput) {

		if (ordineInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		ordineService.inserisciNuovo(ordineInput.buildOrdineModel());
		
		
		
	}

	@GetMapping("/{id}")
	public OrdineDTO findById(@PathVariable(value = "id", required = true) long id) {
		Ordine ordine = ordineService.caricaSingoloOrdineEager(id);
		if (ordine == null)
			throw new OrdineNotFoundException("Ordine not found con id: " + id);

		return OrdineDTO.buildOrdineDTOFromModel(ordine, true, true, true);
	}

	@PutMapping("/{id}")
	public void update(@Valid @RequestBody OrdineDTO ordineInput, @PathVariable(required = true) Long id) {
		Ordine ordine = ordineService.caricaSingoloOrdine(id);

		if (ordine == null)
			throw new OrdineNotFoundException("Ordine not found con id: " + id);

		ordineInput.setId(id);
		ordineService.aggiorna(ordineInput.buildOrdineModel());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		ordineService.rimuovi(id);
	}

	@PostMapping("/search")
	public List<OrdineDTO> search(@RequestBody OrdineDTO example) {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.findByExample(example.buildOrdineModel()), true,
				true, true);
	}
	
	
	@GetMapping(value = "/costoOrdine/{id}")
	public Integer getCostoOrdine(@PathVariable(value = "id", required = true) long id) {
		return ordineService.calcolaPrezzoOrdineTotale(ordineService.caricaSingoloOrdineEager(id).getCodice());
	}
	
	@GetMapping(value = "/ordiniNotClosed")
	public List<OrdineDTO> getAllNotClosed() {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.listAllElementsWithNotClosed(), true, true, true);
	}
	
	@PostMapping(value = "/ricaviIntervallo")
	public Integer getCostoOrdine(@Valid @RequestBody StatisticheDTO example ) {
		return ordineService.calcolaRicaviOrdiniIntervalloDate(example.getDataInizio(),example.getDataFine());
	}
	
	@PostMapping(value = "/numeroPizzeOrdinate")
	public Integer getNumeroPizzeOrdinate(@Valid @RequestBody StatisticheDTO example) {
		return ordineService.calcolaNumeroPizzeOrdinate( example.getDataInizio(),example.getDataFine());
	}
	
	@PostMapping(value = "/numeroOrdiniIntervallo")
	public Integer getNumeroOrdiniIntervallo(@Valid @RequestBody StatisticheDTO example) {
		return ordineService.calcolaNumeroOrdiniIntervallo(example.getDataInizio(),example.getDataFine());
	}
	
	@PostMapping(value = "/OrdiniClienteIntervalloPizza")
	public List<OrdineDTO> getOrdiniClienteIntervalloPizza(@Valid @RequestBody Long clienteId,Long pizzaId,StatisticheDTO example) {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.cercaOrdiniTraDateDiClienteConPizze(clienteId, pizzaId, example),true,true,true);
	}

	@GetMapping(value = "/fattorino")
	public List<OrdineDTO> getOrdiniApertiFattorino(Principal principal) {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.findAllOrdineByClosedAndFattorinoLog(principal.getName()),true,true,true);
	}
}
