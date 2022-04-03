package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.FriendshipResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.FriendshipResponseMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.FriendshipRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindDomainFriendshipService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private FriendshipRepository friendshipRepository;

    public Friendship find(String email) {

        User myself = findDomainMyselfService.find();
        Friendship friendship = fetchFriendship(myself.getEmail(), email);

        return friendship;
    }

    private Friendship fetchFriendship(String emailOne, String emailTwo) {

        Optional<Friendship> friendship = friendshipRepository.findBySenderEmailAndReceiverEmail(emailOne, emailTwo);

        if (!friendship.isPresent())
            friendship = friendshipRepository.findBySenderEmailAndReceiverEmail(emailTwo, emailOne);

        return friendship.isPresent() ? friendship.get() : null;
    }
}
