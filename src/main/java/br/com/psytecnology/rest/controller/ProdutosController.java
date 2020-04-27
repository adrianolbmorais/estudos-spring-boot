package br.com.psytecnology.rest.controller;

import br.com.psytecnology.domain.entity.Produto;
import br.com.psytecnology.domain.repository.ProdutosRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/produtos")
public class ProdutosController {

    private static final String MENSAGEM_PRODUTO_NAO_ENCONTRADO = "Produto não encontrado!";

    private ProdutosRepository produtosRepository;

    public ProdutosController(ProdutosRepository produtosRepository){
        this.produtosRepository = produtosRepository;
    }

    @GetMapping("/{idProduto}")
    public Produto findProdutoById(@PathVariable Integer idProduto){
        return produtosRepository
                .findById(idProduto)
                .orElseThrow(
                        () -> new ResponseStatusException(NOT_FOUND, MENSAGEM_PRODUTO_NAO_ENCONTRADO)
                );
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody @Valid Produto produto){
        return produtosRepository.save(produto);
    }

    @DeleteMapping("/{idProduto}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer idProduto){
        produtosRepository.findById(idProduto).map(
                produto -> {
                    produtosRepository.delete(produto);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, MENSAGEM_PRODUTO_NAO_ENCONTRADO));
    }

    @PutMapping("/{idProduto}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer idProduto, @RequestBody @Valid Produto produto){
        produtosRepository.findById(idProduto).map(
                produtoResult -> {
                    produto.setId(produtoResult.getId());
                    produtosRepository.save(produto);
                    return produto;
                }
        ).orElseThrow( () -> new ResponseStatusException(NOT_FOUND, MENSAGEM_PRODUTO_NAO_ENCONTRADO));
    }

    @GetMapping
    public List<Produto> findProdutoAndFilter(Produto produto){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(CONTAINING);
        Example example = Example.of(produto, matcher);
        return produtosRepository.findAll(example);
    }
}
