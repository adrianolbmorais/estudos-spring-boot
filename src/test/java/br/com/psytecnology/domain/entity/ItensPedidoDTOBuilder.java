package br.com.psytecnology.domain.entity;

import br.com.psytecnology.domain.entity.dto.ItensPedidoDTO;

public class ItensPedidoDTOBuilder {
    private ItensPedidoDTO elemento;
    private ItensPedidoDTOBuilder(){}

    public static ItensPedidoDTOBuilder criarUmItensPedidoDTO() {
        ItensPedidoDTOBuilder builder = new ItensPedidoDTOBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(ItensPedidoDTOBuilder builder) {
        builder.elemento = new ItensPedidoDTO();
        ItensPedidoDTO elemento = builder.elemento;

        elemento.setIdProduto(0);
        elemento.setQuantidade(0);
    }

    public ItensPedidoDTOBuilder comIdProduto(Integer param) {
        elemento.setIdProduto(param);
        return this;
    }

    public ItensPedidoDTOBuilder comQuantidade(Integer param) {
        elemento.setQuantidade(param);
        return this;
    }

    public ItensPedidoDTO agora() {
        return elemento;
    }
}