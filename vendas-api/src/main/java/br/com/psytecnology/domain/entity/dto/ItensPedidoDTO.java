package br.com.psytecnology.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensPedidoDTO {

    @NotNull(message = "{campo.codigo-produto.obrigatorio}")
    @Positive(message = "{campo.codigo-produto.positivo}")
    private Integer idProduto;

    @NotNull(message = "{campo.quantidade-produto.obrigatorio}")
    @Positive(message = "{campo.quantidade-produto.positivo}")
    private Integer quantidade;

    public ItensPedidoDTO idProduto(Integer idProduto){
        this.idProduto = idProduto;
        return this;
    }

    public ItensPedidoDTO quantidade(Integer quantidade){
        this.quantidade = quantidade;
        return this;
    }
}
