package br.com.psytecnology.domain.entity.dto;

import br.com.psytecnology.domain.entity.ItemPedido;
import br.com.psytecnology.domain.entity.Pedido;
import br.com.psytecnology.domain.entity.Produto;
import br.com.psytecnology.domain.repository.ProdutosRepository;
import br.com.psytecnology.exceptions.RegraNegocioException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class MapperDtoToEntity {

    private final ProdutosRepository produtosRepository;

    public MapperDtoToEntity(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public List<ItemPedido> converterItensPedidosDTOtoEntity(Pedido pedido, List<ItensPedidoDTO> itensPedidoDTO){

        if(itensPedidoDTO.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem itens");
        }

        return itensPedidoDTO.stream().map( dto -> {
            Integer idProduto = dto.getIdProduto();

            Produto produto = produtosRepository
                    .findById(idProduto)
                    .orElseThrow(
                            () -> new RegraNegocioException("Código de Produto inválido: " + idProduto)
                    );

            ItemPedido itemPedido = new ItemPedido();

            itemPedido
                    .quantidade(dto.getQuantidade())
                    .pedido(pedido)
                    .produto(produto);

            return itemPedido;

        }).collect(toList());

    }

    public InformacoesPedidoDTO converterPedidoToDto(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converterItensPedidoToDto(pedido.getItens()))
                .build();
    }

    public List<InformacoesItemPedidoDTO> converterItensPedidoToDto(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacoesItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}
