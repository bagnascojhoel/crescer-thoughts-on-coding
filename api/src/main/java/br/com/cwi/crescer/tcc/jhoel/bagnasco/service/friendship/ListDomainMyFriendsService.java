package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.FriendshipStatus;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.FriendshipRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListDomainMyFriendsService {
    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    public List<User> list() {
        User myself = findDomainMyselfService.find();
        List<Friendship> friendships =
                friendshipRepository.findBySenderEmailOrReceiverEmailAndStatus(myself.getEmail(), myself.getEmail(), FriendshipStatus.ACCEPTED);

        return friendships.stream()
                .map(f -> f.getSender().equals(myself) ? f.getReceiver() : f.getSender())
                .collect(Collectors.toList());
    }

}
