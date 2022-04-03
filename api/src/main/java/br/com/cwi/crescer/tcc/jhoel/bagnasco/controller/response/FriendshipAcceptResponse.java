package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FriendshipAcceptResponse {

    private User sender;

    private LocalDateTime acceptedTime;

}
