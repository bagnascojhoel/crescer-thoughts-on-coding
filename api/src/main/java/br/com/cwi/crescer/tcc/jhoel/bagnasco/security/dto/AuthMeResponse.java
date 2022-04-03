package br.com.cwi.crescer.tcc.jhoel.bagnasco.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthMeResponse {

    private String email;

    private String firstName;

    private String lastName;

    private List<String> roles;

}
