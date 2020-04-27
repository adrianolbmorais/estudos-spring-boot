package br.com.psytecnology.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    @Column(name = "descricao")
    private String descricao;

    @Positive(message = "{campo.preco.positivo}")
    @NotNull(message = "{campo.preco.obrigatorio}")
    @Column(name = "preco_unitario", precision = 20, scale = 2)
    private BigDecimal preco;

    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private Set<ItemPedido> itensPedido;

    public Produto id(Integer id) {
        this.id = id;
        return this;
    }

    public Produto descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Produto preco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }

    public Produto itensPedido(Set<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
        return this;
    }
}
