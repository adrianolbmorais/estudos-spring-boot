package br.com.psytecnology.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    public ItemPedido id(Integer id) {
        this.id = id;
        return this;
    }

    public ItemPedido pedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public ItemPedido produto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public ItemPedido quantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }
}
