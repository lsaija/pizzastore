package it.prova.pizzastore.service.ordine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.repository.ordine.OrdineRepository;

@Service
@Transactional
public class OrdineServiceImpl implements OrdineService{

	@Autowired
	private OrdineRepository ordineRepository;
	
	@Override
	public List<Ordine> listAllOrdini() {
		return (List<Ordine>) ordineRepository.findAll();
		
	}
	
	public List<Ordine> listAllElementsSingoloCliente(Long id) {
		return ordineRepository.findAllOrdineByCliente(id);
	}


	@Override
	public Ordine caricaSingoloOrdine(Long id) {
	   return ordineRepository.findById(id).orElse(null);
	}

	@Override
	public Ordine caricaSingoloOrdineEager(Long id) {
		return ordineRepository.findByIdEager(id).orElse(null);
	}

	@Override
	public void aggiorna(Ordine ordineInstance) {
		ordineRepository.save(ordineInstance);
		
	}

	@Override
	public void inserisciNuovo(Ordine ordineInstance) {
		ordineInstance.setClosed(false);
		ordineRepository.save(ordineInstance);		
	}

	@Override
	public void rimuovi(Long idToRemove) {
		Ordine ordineInstance= ordineRepository.findById(idToRemove).orElse(null);
		
		ordineInstance.setClosed(true);
		
		ordineRepository.save(ordineInstance);
		
	}

	@Override
	public List<Ordine> findByExample(Ordine example) {
		return ordineRepository.findByExample(example);
	}

}
