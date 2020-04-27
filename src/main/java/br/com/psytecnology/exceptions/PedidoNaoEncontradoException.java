package br.com.psytecnology.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
