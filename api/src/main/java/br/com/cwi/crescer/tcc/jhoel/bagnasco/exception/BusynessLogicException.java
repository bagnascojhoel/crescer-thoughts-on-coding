package br.com.cwi.crescer.tcc.jhoel.bagnasco.exception;

import org.springframework.http.HttpStatus;

public class BusynessLogicException extends AbstractException {
    public BusynessLogicException(String error) {
        super(HttpStatus.BAD_REQUEST, error);
    }
}
