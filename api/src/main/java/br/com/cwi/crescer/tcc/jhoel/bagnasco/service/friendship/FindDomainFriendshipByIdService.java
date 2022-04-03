package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.FriendshipRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.validators.FriendshipDoesNotExistValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindDomainFriendshipByIdService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private FriendshipDoesNotExistValidator friendshipDoesNotExistValidator;

    public Friendship find(String id) {
        Optional<Friendship> optionalFriendship = friendshipRepository.findById(Long.parseLong(id));

        friendshipDoesNotExistValidator.validate(optionalFriendship);

        return optionalFriendship.get();
    }

}
