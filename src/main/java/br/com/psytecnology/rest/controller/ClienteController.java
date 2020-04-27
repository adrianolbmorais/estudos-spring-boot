package br.com.psytecnology.rest.controller;

import br.com.psytecnology.domain.entity.Cliente;
import br.com.psytecnology.domain.repository.ClientesRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/clientes")
@Api("Api Clientes")
public class ClienteController {

    private static final String MENSAGEM_CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository){
        this.clientesRepository = clientesRepository;
    }

    @GetMapping("/{idCliente}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado")
    })
    public Cliente findProdutoById(@PathVariable Integer idCliente){
        return clientesRepository
                .findById(idCliente)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, MENSAGEM_CLIENTE_NAO_ENCONTRADO)
                );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public Cliente save(@RequestBody @Valid Cliente cliente){
        return clientesRepository.save(cliente);
    }

    @DeleteMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer idCliente){
        clientesRepository.findById(idCliente).map(
                cliente -> {
                    clientesRepository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, MENSAGEM_CLIENTE_NAO_ENCONTRADO));
    }

    @PutMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer idCliente, @RequestBody @Valid Cliente cliente){
        clientesRepository.findById(idCliente).map(
            clienteExistente -> {
                   cliente.setId(clienteExistente.getId());
                   clientesRepository.save(cliente);
                   return cliente;
            }
        ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, MENSAGEM_CLIENTE_NAO_ENCONTRADO));
    }

    @GetMapping
    public List<Cliente> findClienteAndFilter(Cliente cliente){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(cliente, matcher);
        return clientesRepository.findAll(example);
    }
}
