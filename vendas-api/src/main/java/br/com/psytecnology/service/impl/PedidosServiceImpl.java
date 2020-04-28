package br.com.psytecnology.service.impl;

import br.com.psytecnology.domain.entity.Cliente;
import br.com.psytecnology.domain.entity.ItemPedido;
import br.com.psytecnology.domain.entity.Pedido;
import br.com.psytecnology.domain.enums.StatusPedido;
import br.com.psytecnology.domain.repository.ClientesRepository;
import br.com.psytecnology.domain.repository.ItemPedidoRepository;
import br.com.psytecnology.domain.repository.PedidosRepository;
import br.com.psytecnology.domain.repository.ProdutosRepository;
import br.com.psytecnology.exceptions.PedidoNaoEncontradoException;
import br.com.psytecnology.exceptions.RegraNegocioException;
import br.com.psytecnology.domain.entity.dto.InformacoesPedidoDTO;
import br.com.psytecnology.domain.entity.dto.MapperDtoToEntity;
import br.com.psytecnology.domain.entity.dto.PedidoDTO;
import br.com.psytecnology.service.PedidosService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PedidosServiceImpl implements PedidosService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    private final MapperDtoToEntity mapperDtoToEntity;

    @Override
    public Optional<Pedido> findPedidoById(Integer idPedido) {
        return pedidosRepository.findById(idPedido);
    }

    @Transactional
    @Override
    public Pedido save(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getIdCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(
                        () -> new RegraNegocioException("Código de cliente inválido: " + idCliente)
                );

        Pedido pedido = new Pedido();
        pedido.total(pedidoDTO.getValorTotal())
                .dataPedido(LocalDate.now())
                .cliente(cliente)
                .status(StatusPedido.REALIZADO);

        List<ItemPedido> itensPedidos = mapperDtoToEntity.converterItensPedidosDTOtoEntity(pedido, pedidoDTO.getItensPedidoDTO());
        pedidosRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedidos);

        pedido.setItens(itensPedidos);

        return pedido;
    }

    @Override
    public InformacoesPedidoDTO obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id)
                .map(pedido -> mapperDtoToEntity.converterPedidoToDto(pedido))
                .orElseThrow(
                        () -> new PedidoNaoEncontradoException("Pedido com código ".concat(id.toString()).concat(" não encontrado."))
                );
    }

    @Override
    @Transactional
    public Pedido atualizarStatus(Integer idPedido, StatusPedido statusPedido) {
        return pedidosRepository.findById(idPedido)
                .map(
                    pedido -> {
                        pedido.status(statusPedido);
                        return pedidosRepository.save(pedido);
                    }
                ).orElseThrow(
                        () -> new PedidoNaoEncontradoException("Pedido com código ".concat(idPedido.toString()).concat(" não encontrado."))
                );
    }

    @Override
    public void delete(Integer idPedido){
//        try{
            pedidosRepository.deleteById(idPedido);
//        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
//           throw new PedidoNaoEncontradoException("Pedido com código ".concat(idPedido.toString()).concat(" não encontrado."));
//        }
    }

}
