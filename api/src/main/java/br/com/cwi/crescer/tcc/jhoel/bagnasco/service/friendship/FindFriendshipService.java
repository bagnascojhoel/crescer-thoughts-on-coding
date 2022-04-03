package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.FriendshipResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.FriendshipResponseMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.UserResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindFriendshipService {

    @Autowired
    private FindDomainFriendshipService findDomainFriendshipService;

    @Autowired
    private FriendshipResponseMapper friendshipResponseMapper;

    @Autowired
    private UserResponseMapper userResponseMapper;

    public FriendshipResponse find(String email) {
        Friendship friendship = findDomainFriendshipService.find(email);

        FriendshipResponse response;

        if (friendship != null) {
            response = friendshipResponseMapper.map(
                    friendship,
                    userResponseMapper.map(friendship.getSender()),
                    userResponseMapper.map(friendship.getReceiver())
            );
        } else response = null;

        return response;
    }
}
