package it.prova.pizzastore.repository.ordine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Ordine;

public class CustomOrdineRepositoryImpl implements CustomOrdineRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Ordine> findByExample(Ordine example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");

		if (example.getData() != null) {
			whereClauses.add("o.data >= :data ");
			paramaterMap.put("data", example.getData());
		}
		
		if (example.isClosed()==false) {
			whereClauses.add(" o.closed =:closed ");
			paramaterMap.put("closed", example.isClosed());
		}
		
		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" o.codice like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (example.getCostoTotale()>0) {
			whereClauses.add(" o.costoTotale >= :costoTotale ");
			paramaterMap.put("costoTotale", example.getCostoTotale());
		}
	
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
