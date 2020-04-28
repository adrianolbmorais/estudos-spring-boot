package br.com.psytecnology.domain.entity;

import br.com.psytecnology.domain.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;


public class PedidoBuilder {
    private Pedido elemento;
    private PedidoBuilder(){}

    public static PedidoBuilder criaUmPedido() {

        PedidoBuilder builder = new PedidoBuilder();

        inicializarDadosPadroes(builder);
            return builder;
        }

        public static void inicializarDadosPadroes(PedidoBuilder builder) {
            builder.elemento = new Pedido();
            Pedido elemento = builder.elemento;


            elemento.setId(0);
            elemento.setCliente(null);
            elemento.setDataPedido(null);
            elemento.setTotal(null);
            elemento.setStatus(null);
            elemento.setItens(null);
        }

        public PedidoBuilder comId(Integer param) {
            elemento.setId(param);
            return this;
        }

        public PedidoBuilder comCliente(Cliente param) {
            elemento.setCliente(param);
            return this;
        }

        public PedidoBuilder comDataPedido(LocalDate param) {
            elemento.setDataPedido(param);
            return this;
        }

        public PedidoBuilder comTotal(BigDecimal param) {
            elemento.setTotal(param);
            return this;
        }

        public PedidoBuilder comStatus(StatusPedido param) {
            elemento.setStatus(param);
            return this;
        }

        public PedidoBuilder comListaItens(ItemPedido... params) {
            elemento.setItens(Arrays.asList(params));
            return this;
        }

        public Pedido agora() {
            return elemento;
        }
}

