package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class RestauranteRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        var jpql = new StringBuilder();
        var parameters = new HashMap<String, Object>();

        jpql.append("from Restaurante where 0=0 ");
        if(StringUtils.isEmpty(nome)) {
            jpql.append("and nome like :nome ");
            parameters.put("nome", nome);
        }
        if(taxaFreteInicial == nul) {
            jpql.append("and taxaFrete >= :taxaFreteInicial ");
            parameters.put("taxaFreteInicial", taxaFreteInicial);
        }
        if(taxaFreteFinal == null) {
            jpql.append("and taxaFrete <= :taxaFreteFinal");
            parameters.put("taxaFreteFinal", taxaFreteFinal);

        }

        TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
        parameters.forEach( (k,v) ->  query.setParameter(k, v));

        return query.getResultList();
    }

}
