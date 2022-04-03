package br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.FriendshipRequestResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import org.springframework.stereotype.Component;

@Component
public class FriendshipRequestResponseMapper {
    public FriendshipRequestResponse map(Friendship friendship) {
        FriendshipRequestResponse result = new FriendshipRequestResponse();

        result.setId(friendship.getId());
        result.setSender(friendship.getSender());

        return result;
    }
}
