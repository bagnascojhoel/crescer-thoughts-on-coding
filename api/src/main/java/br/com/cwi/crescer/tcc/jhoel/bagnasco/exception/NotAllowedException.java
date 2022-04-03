package br.com.cwi.crescer.tcc.jhoel.bagnasco.exception;

import org.springframework.http.HttpStatus;

public class NotAllowedException extends AbstractException {

    public NotAllowedException(String error) {
        super(HttpStatus.UNAUTHORIZED, error);
    }

}
