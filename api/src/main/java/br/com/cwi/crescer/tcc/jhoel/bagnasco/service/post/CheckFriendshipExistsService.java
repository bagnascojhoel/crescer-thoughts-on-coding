package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.FriendshipStatus;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.FriendshipRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckFriendshipExistsService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private FriendshipRepository friendshipRepository;

    public boolean check(String email) {
        User myself = findDomainMyselfService.find();

        return friendshipRepository.findBySenderEmailAndReceiverEmailAndStatus(myself.getEmail(), email, FriendshipStatus.ACCEPTED).isPresent()
                || friendshipRepository.findBySenderEmailAndReceiverEmailAndStatus(email, myself.getEmail(), FriendshipStatus.ACCEPTED).isPresent();
    }

}
