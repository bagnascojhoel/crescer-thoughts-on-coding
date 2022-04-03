package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PartialUpdateUserRequest {

    @NotNull(message = "Name cannot be null.")
    @NotEmpty(message = "Name cannot be empty.")
    @Size(min = 3, max = 255, message = "A name must have it's length between 3 and 255.")
    private String name;

    @Size(max = 50, message = "Nickname must have it's length between 3 and 50.")
    private String nickname;

    @Size(max = 512, message = "Profile photo must at most 512 characters.")
    private String profilePhoto;

}
