package com.algaworks.algafood.domain.repository.restaurante;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long> {

    @Query("from Restaurante r join r.cozinha join fetch r.formasPagamento")
    List<Restaurante> findAll();

    //Resolvida com named query
    @Query(name = "nomeDaQuery")
    List<Restaurante> consultarPorNome(String nome);

    List<Restaurante> findByTaxaFreteBetween(BigDecimal tacaInicial, BigDecimal taxaFinal);

    Optional<Restaurante> findFirstByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    boolean existsByNome(String nome);

    Long countByCozinhaId(Long cozinhaId);

    //A implementação dessa query está no RestauranteRepositoryImpl
    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}
