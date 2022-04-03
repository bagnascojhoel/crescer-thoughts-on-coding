package br.com.cwi.crescer.tcc.jhoel.bagnasco.security.dto;

import lombok.*;

import java.util.List;

@Data
public class AuthRequest {

    private String email;

    private String firstName;

    @Setter(AccessLevel.NONE)
    private String lastName = "NULL";

    private String password;

    private List<String> roles;

}
