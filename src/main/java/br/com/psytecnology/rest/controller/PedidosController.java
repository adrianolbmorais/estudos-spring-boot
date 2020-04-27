package br.com.psytecnology.rest.controller;

import br.com.psytecnology.domain.entity.Pedido;
import br.com.psytecnology.domain.enums.StatusPedido;
import br.com.psytecnology.domain.entity.dto.InformacoesPedidoDTO;
import br.com.psytecnology.domain.entity.dto.PedidoDTO;
import br.com.psytecnology.domain.entity.dto.StatusPedidoDTO;
import br.com.psytecnology.service.PedidosService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/pedidos")
public class PedidosController {

    private PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService){
        this.pedidosService = pedidosService;
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return pedidosService
                .obterPedidoCompleto(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO pedidoDTO){
        Pedido pedido = pedidosService.save(pedidoDTO);
        return pedido.getId();
    }

    @DeleteMapping("/{idPedido}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer idPedido){
        pedidosService.delete(idPedido);
    }

    @PatchMapping("/{idPedido}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer idPedido, @RequestBody @Valid StatusPedidoDTO statuPedido){
        String novoStatus = statuPedido.getStatus();
        pedidosService.atualizarStatus(idPedido, StatusPedido.valueOf(novoStatus));
    }

}
