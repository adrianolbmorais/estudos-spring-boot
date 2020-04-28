package br.com.psytecnology.service;

import br.com.psytecnology.domain.entity.Pedido;
import br.com.psytecnology.domain.enums.StatusPedido;
import br.com.psytecnology.domain.entity.dto.InformacoesPedidoDTO;
import br.com.psytecnology.domain.entity.dto.PedidoDTO;

import java.util.Optional;

public interface PedidosService {

    Optional<Pedido> findPedidoById(Integer idPedido);

    Pedido save(PedidoDTO pedidoDTO);

    InformacoesPedidoDTO obterPedidoCompleto(Integer id);

    Pedido atualizarStatus(Integer idPedido, StatusPedido statusPedido);

    void delete(Integer idPedido);

}

