package br.com.psytecnology.domain.entity;

import br.com.psytecnology.domain.entity.dto.ItensPedidoDTO;
import br.com.psytecnology.domain.entity.dto.PedidoDTO;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTOBuilder {

        private PedidoDTO elemento;

        private PedidoDTOBuilder(){}

        public static PedidoDTOBuilder criarUmPedidoDTO() {
            PedidoDTOBuilder builder = new PedidoDTOBuilder();
            inicializarDadosPadroes(builder);
            return builder;
        }

        public static void inicializarDadosPadroes(PedidoDTOBuilder builder) {
            builder.elemento = new PedidoDTO();
            PedidoDTO elemento = builder.elemento;


            elemento.setIdCliente(0);
            elemento.setValorTotal(null);
            elemento.setItensPedidoDTO(null);
        }

        public PedidoDTOBuilder comIdCliente(Integer param) {
            elemento.setIdCliente(param);
            return this;
        }

        public PedidoDTOBuilder comValorTotal(BigDecimal param) {
            elemento.setValorTotal(param);
            return this;
        }

        public PedidoDTOBuilder comListaItensPedidoDTO(List<ItensPedidoDTO> params) {
            elemento.setItensPedidoDTO(params);
            return this;
        }

        public PedidoDTO agora() {
            return elemento;
        }
}