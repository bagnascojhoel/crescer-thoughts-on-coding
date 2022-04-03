package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.FriendshipRequestResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.FriendshipStatus;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.FriendshipRequestResponseMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.FriendshipRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListMyFriendshipRequestsService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private FriendshipRequestResponseMapper mapper;

    public List<FriendshipRequestResponse> list() {
        User myself = findDomainMyselfService.find();

        List<Friendship> friendshipsReceived = friendshipRepository.findByReceiverAndStatus(myself, FriendshipStatus.PENDING);

        return friendshipsReceived.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }
}
