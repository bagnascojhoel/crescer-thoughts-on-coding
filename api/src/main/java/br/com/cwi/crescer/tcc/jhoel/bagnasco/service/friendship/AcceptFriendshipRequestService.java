package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.FriendshipStatus;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AcceptFriendshipRequestService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private FindDomainFriendshipByIdService findDomainFriendshipByIdService;


    public Friendship accept(String id) {
        Friendship friendship = findDomainFriendshipByIdService.find(id);

        friendship.setStatus(FriendshipStatus.ACCEPTED);
        friendship.setLastStatusUpdate(LocalDateTime.now());

        return friendshipRepository.save(friendship);
    }
}
