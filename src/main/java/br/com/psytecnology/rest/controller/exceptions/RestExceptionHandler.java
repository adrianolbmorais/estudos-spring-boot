package br.com.psytecnology.rest.controller.exceptions;

import br.com.psytecnology.exceptions.PedidoNaoEncontradoException;
import br.com.psytecnology.exceptions.RegraNegocioException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleMethodRegraNegocioException(RegraNegocioException regraNegocioException){
        return new ApiErrors(regraNegocioException.getMessage());
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErrors handleMethodPedidoNaoEncontrado(PedidoNaoEncontradoException pedidoNaoEncontradoException){
        return new ApiErrors(pedidoNaoEncontradoException.getMessage());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handlerMethodEmptyResultDataAccessException(EmptyResultDataAccessException emptyResultDataAccessException){
        return new ApiErrors(emptyResultDataAccessException.getMessage());
    }

}
