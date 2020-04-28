package br.com.psytecnology.domain.repository;

import br.com.psytecnology.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JpaRepositoriesClienteRepository extends JpaRepository<Cliente, Integer> {

    boolean existsByNome(String nome);

    List<Cliente> findByNomeOrId(String nome, Integer id);

    @Transactional
    void deleteByNome(String nome);

    @Transactional
    @Modifying
    @Query(value = "delete from Cliente cli where cli.nome = :nome")
    void deleteByNomeQuery(@Param("nome") String nome);

    Cliente findOneByNome(String nome);

    List<Cliente> findByNomeLike(String nome);

    @Query(value = "select cli from Cliente cli where cli.nome like %:nome%")
    List<Cliente> buscarPorNomeQuery(@Param("nome") String nome);

    @Query(value = "select * from cliente cli where cli.nome like %:nome%", nativeQuery = true)
    List<Cliente> buscarPorNomeNativQuery(@Param("nome") String nome);
}
