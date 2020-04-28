package br.com.psytecnology.domain.entity.dto;

import br.com.psytecnology.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    @Positive(message = "{campo.codigo-cliente.positivo}")
    private Integer idCliente;

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    @Positive(message = "{campo.total-pedido.positivo}")
    private BigDecimal valorTotal;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItensPedidoDTO> itensPedidoDTO;

    public PedidoDTO idCliente(Integer idCliente){
        this.idCliente = idCliente;
        return this;
    }

    public PedidoDTO total(BigDecimal valorTotal){
        this.valorTotal = valorTotal;
        return this;
    }

    public PedidoDTO itensPedidoDTO(List<ItensPedidoDTO> itensPedidoDTO){
        this.itensPedidoDTO = itensPedidoDTO;
        return this;
    }

}
