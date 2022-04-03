package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.FriendshipStatus;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import lombok.Data;

@Data
public class FriendshipRequestResponse {

    private Long id;

    private User sender;

}
