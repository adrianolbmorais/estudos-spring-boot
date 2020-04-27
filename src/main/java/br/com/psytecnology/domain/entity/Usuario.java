package br.com.psytecnology.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login")
    @NotBlank(message = "{campo.login.obrigatorio}")
    private String nomeUsuario;

    @Column
    @NotBlank(message = "{campo.senha.obrigatorio}")
    private String senha;

    @Column
    private boolean admin;
}
