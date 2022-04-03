package br.com.cwi.crescer.tcc.jhoel.bagnasco.validators;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.exception.NotFoundException;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class UserDoesNotExistValidator {
    public void validate(Optional<User> userOptional) {
        if (!userOptional.isPresent())
            throw new NotFoundException("User doesn't exist");
    }
}
