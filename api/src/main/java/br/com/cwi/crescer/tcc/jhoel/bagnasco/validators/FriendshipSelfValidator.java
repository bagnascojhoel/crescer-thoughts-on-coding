package br.com.cwi.crescer.tcc.jhoel.bagnasco.validators;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.exception.BusynessLogicException;
import org.springframework.stereotype.Component;

@Component
public class FriendshipSelfValidator {
    public void validate(User myself, User someone) {
        if (myself.equals(someone))
            throw new BusynessLogicException("Cannot befriend yourself");
    }
}
