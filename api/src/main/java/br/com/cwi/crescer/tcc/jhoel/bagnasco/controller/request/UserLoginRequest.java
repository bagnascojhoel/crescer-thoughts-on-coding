package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class UserLoginRequest {

    @NotNull(message = "An e-mail is required.")
    @Size(max = 255, message = "The maximum length of an e-mail is 255 characters.")
    @Email(message = "The given e-mail is invalid.")
    private String email;

    @NotNull(message = "A password is required.")
    @Size(max = 128, message = "The maximum length of a password is 128 characters.")
    private String password;

}
