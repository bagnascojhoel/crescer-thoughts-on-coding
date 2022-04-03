package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UserSignUpRequest {

    @NotNull(message = "Name cannot be null.")
    @NotEmpty(message = "Name cannot be empty.")
    @Size(min = 3, max = 255, message = "A name must have it's length between 3 and 255.")
    private String name;

    @NotNull(message = "E-mail cannot be null.")
    @NotEmpty(message = "E-mail cannot be empty.")
    @Size(max = 255, message = "E-mail must have at most 255 characters.")
    @Email(message = "E-mail is invalid.")
    private String email;

    @NotNull(message = "Birth date cannot be null.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotNull(message = "Password cannot be null.")
    @NotEmpty(message = "Password cannot be empty.")
    @Size(max = 128, message = "Password must have at most 128 characters.")
    private String password;

    @Size(max = 50, message = "Nickname must have it's length between 3 and 50.")
    private String nickname;

    @Size(max = 512, message = "Profile photo must at most 512 characters.")
    private String profilePhoto;

}
