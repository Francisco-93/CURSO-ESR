package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntFunction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class RestauranteRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

//    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//        var jpql = new StringBuilder();
//        var parameters = new HashMap<String, Object>();
//
//        jpql.append("from Restaurante where 0=0 ");
//        if(!StringUtils.isEmpty(nome)) {
//            jpql.append("and nome like :nome ");
//            parameters.put("nome", nome);
//        }
//        if(taxaFreteInicial == null) {
//            jpql.append("and taxaFrete >= :taxaFreteInicial ");
//            parameters.put("taxaFreteInicial", taxaFreteInicial);
//        }
//        if(taxaFreteFinal == null) {
//            jpql.append("and taxaFrete <= :taxaFreteFinal");
//            parameters.put("taxaFreteFinal", taxaFreteFinal);
//
//        }
//
//        TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
//        parameters.forEach( (k,v) ->  query.setParameter(k, v));
//
//        return query.getResultList();
//    }

    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        var predicates = new ArrayList<Predicate>();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = criteriaBuilder.createQuery(Restaurante.class);
        Root<Restaurante> from = criteria.from(Restaurante.class);

        if(!StringUtils.isEmpty(nome)) {
            predicates.add(criteriaBuilder.like(from.get("nome"), "%"+nome+"%"));
        }
        if(taxaFreteInicial != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(from.get("taxaFrete"), taxaFreteInicial));
        }
        if(taxaFreteFinal != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(from.get("taxaFrete"), taxaFreteFinal));
        }

        criteria.where(predicates.toArray(predicates.toArray(new Predicate[0])));

        TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }
}
