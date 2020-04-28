package br.com.psytecnology.domain.repository;

import br.com.psytecnology.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    @Query(" select cli from Cliente cli left join fetch cli.pedidos where cli.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
