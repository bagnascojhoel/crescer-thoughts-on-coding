package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.FriendshipStatus;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DenyFriendshipRequestService {

    @Autowired
    private FindDomainFriendshipByIdService findDomainFriendshipByIdService;

    @Autowired
    private FriendshipRepository friendshipRepository;

    public void deny(String id) {
        Friendship friendship = findDomainFriendshipByIdService.find(id);

        friendship.setStatus(FriendshipStatus.DENIED);
        friendship.setLastStatusUpdate(LocalDateTime.now());

        friendshipRepository.save(friendship);
    }
}
