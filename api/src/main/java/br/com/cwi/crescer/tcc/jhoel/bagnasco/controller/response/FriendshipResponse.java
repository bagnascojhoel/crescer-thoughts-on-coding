package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.FriendshipStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendshipResponse {

    private Long id;

    private UserResponse sender;

    private UserResponse receiver;

    private FriendshipStatus status;

    private LocalDateTime lastStatusUpdate;

}
