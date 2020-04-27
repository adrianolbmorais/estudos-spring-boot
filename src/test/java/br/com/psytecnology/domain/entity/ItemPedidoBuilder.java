package br.com.psytecnology.domain.entity;

public class ItemPedidoBuilder {
        private ItemPedido elemento;
        private ItemPedidoBuilder(){}

        public static ItemPedidoBuilder criarUmItemPedido() {
        ItemPedidoBuilder builder = new ItemPedidoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
        }

        public static void inicializarDadosPadroes(ItemPedidoBuilder builder) {
        builder.elemento = new ItemPedido();
        ItemPedido elemento = builder.elemento;

        elemento.setId(0);
        elemento.setPedido(null);
        elemento.setProduto(null);
        elemento.setQuantidade(0);
        }

        public ItemPedidoBuilder comId(Integer param) {
        elemento.setId(param);
        return this;
        }

        public ItemPedidoBuilder comPedido(Pedido param) {
        elemento.setPedido(param);
        return this;
        }

        public ItemPedidoBuilder comProduto(Produto param) {
        elemento.setProduto(param);
        return this;
        }

        public ItemPedidoBuilder comQuantidade(Integer param) {
        elemento.setQuantidade(param);
        return this;
        }

        public ItemPedido agora() {
        return elemento;
        }
}