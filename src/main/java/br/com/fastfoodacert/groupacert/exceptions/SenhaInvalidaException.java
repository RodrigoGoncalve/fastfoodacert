package br.com.fastfoodacert.groupacert.exceptions;

public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException() {
        super("senha invalida");
    }
}
