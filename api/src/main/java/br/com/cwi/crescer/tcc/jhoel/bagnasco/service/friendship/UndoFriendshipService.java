package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.FriendshipRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindMyselfService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.validators.FriendshipDoesNotExistValidator;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.validators.FriendshipNotAcceptedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UndoFriendshipService {

    @Autowired
    private FindDomainFriendshipService findDomainFriendshipService;

    @Autowired
    private FriendshipDoesNotExistValidator friendshipDoesNotExistValidator;

    @Autowired
    private FriendshipNotAcceptedValidator friendshipNotAcceptedValidator;

    @Autowired
    private FriendshipRepository friendshipRepository;

    public void undo(String email) {
        Friendship friendship = findDomainFriendshipService.find(email);

        friendshipDoesNotExistValidator.validate(friendship);

        friendshipNotAcceptedValidator.validate(friendship);

        friendshipRepository.deleteById(friendship.getId());
    }
}
