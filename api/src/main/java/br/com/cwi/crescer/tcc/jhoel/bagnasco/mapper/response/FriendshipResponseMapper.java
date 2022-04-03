package br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.FriendshipResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import org.springframework.stereotype.Component;

@Component
public class FriendshipResponseMapper {

    public FriendshipResponse map(Friendship friendship, UserResponse sender, UserResponse receiver) {
        FriendshipResponse result = new FriendshipResponse();

        result.setId(friendship.getId());
        result.setStatus(friendship.getStatus());
        result.setLastStatusUpdate(friendship.getLastStatusUpdate());
        result.setSender(sender);
        result.setReceiver(receiver);

        return result;
    }
}
