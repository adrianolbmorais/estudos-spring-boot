package br.com.psytecnology.domain.repository;

import br.com.psytecnology.domain.entity.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcClienteRepository {

    private static String INSERT = "insert into cliente(nome) values(?)";
    private static String UPDATE = "update cliente set nome = ? where id = ?";
    private static String DELETE = "delete from cliente where id = ?";
    private static String FIND_BY_NAME = "select * from cliente where nome like ? ";
    private static String SELECT_ALL = "select * from cliente";

    private JdbcTemplate jdbcTemplate;

    public JdbcClienteRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Cliente save(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente update(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void delete(Cliente cliente){
        jdbcTemplate.update(DELETE, new Object[]{cliente.getId()});
    }

    public List<Cliente> findByName(String nome){
        return jdbcTemplate.query(FIND_BY_NAME, new Object[]{ "%" + nome + "%"}, getRowMapper());
    }

    public List<Cliente> findAll(){
        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }

    private RowMapper<Cliente> getRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                return  new Cliente(
                            resultSet.getInt("id"),
                            resultSet.getString("nome")
                        );
            }
        };
    }
}
