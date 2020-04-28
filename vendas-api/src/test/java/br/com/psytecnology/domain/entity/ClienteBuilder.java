package br.com.psytecnology.domain.entity;

import java.lang.Integer;
import java.util.Arrays;
import java.lang.String;
import java.util.Set;
import br.com.psytecnology.domain.entity.Cliente;

public class ClienteBuilder {
    private Cliente elemento;
    private ClienteBuilder(){}

    public static ClienteBuilder criarUmCliente() {
        ClienteBuilder builder = new ClienteBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(ClienteBuilder builder) {
        builder.elemento = new Cliente();
        Cliente elemento = builder.elemento;

        elemento.setId(0);
        elemento.setNome("");
        elemento.setCpf("");
        elemento.setPedidos(null);
    }

    public ClienteBuilder comId(Integer param) {
        elemento.setId(param);
        return this;
    }

    public ClienteBuilder comNome(String param) {
        elemento.setNome(param);
        return this;
    }

    public ClienteBuilder comCpf(String param) {
        elemento.setCpf(param);
        return this;
    }

    public ClienteBuilder comPedidos(Set<Pedido> param) {
        elemento.setPedidos(param);
        return this;
    }

    public Cliente agora() {
        return elemento;
    }
}

