package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserLoginResponse {

    private String email;

    private String token;

}
