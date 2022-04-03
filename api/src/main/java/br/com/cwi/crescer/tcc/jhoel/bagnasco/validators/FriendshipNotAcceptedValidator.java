package br.com.cwi.crescer.tcc.jhoel.bagnasco.validators;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.FriendshipStatus;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.exception.BusynessLogicException;
import org.springframework.stereotype.Component;

@Component
public class FriendshipNotAcceptedValidator {
    public void validate(Friendship friendship) {
        if (!friendship.getStatus().equals(FriendshipStatus.ACCEPTED)) {
            throw new BusynessLogicException("Friendship not accepted");
        }
    }
}
