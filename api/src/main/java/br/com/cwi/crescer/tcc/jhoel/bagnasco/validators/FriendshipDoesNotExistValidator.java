package br.com.cwi.crescer.tcc.jhoel.bagnasco.validators;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FriendshipDoesNotExistValidator {
    public void validate(Optional<Friendship> friendshipOptional) {
        if (!friendshipOptional.isPresent())
            throw new NotFoundException("Friendship does not exist");
    }

    public void validate(Friendship friendship) {
        if (friendship == null)
            throw new NotFoundException("Friendship does not exist");
    }
}
