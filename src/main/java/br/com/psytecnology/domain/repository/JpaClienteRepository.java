package br.com.psytecnology.domain.repository;

import br.com.psytecnology.domain.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaClienteRepository {

    private EntityManager entityManager;

    public JpaClienteRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public Cliente save(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente update(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void delete(Cliente cliente){
        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> findByName(Cliente cliente){
        String jpql = " select cliente from Cliente cliente where cliente.nome like :nome ";
        TypedQuery<Cliente> query =  entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + cliente.getNome() + "%");
        return query.getResultList();
    }

    @Transactional
    public List<Cliente> findAll(){
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }
}
