package br.com.cwi.crescer.tcc.jhoel.bagnasco.validators;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.exception.BusynessLogicException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FriendshipAlreadyExistsValidator {
    public void validate(Optional<Friendship> optionalFriendship) {
        if (optionalFriendship.isPresent())
            throw new BusynessLogicException("This users are friends already");
    }
}
