package br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.domain;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserSignUpRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.dto.AuthUserDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User map(UserSignUpRequest request) {
        User result = new User();

        result.setName(request.getName());
        result.setEmail(request.getEmail());
        result.setBirthDate(request.getBirthDate());
        result.setNickname(request.getNickname());
        result.setProfilePhoto(request.getProfilePhoto());

        return result;
    }

    public User map(AuthUserDetailsResponse response) {
        User result = new User();

        result.setName(response.getFirstName());
        result.setEmail(response.getEmail());

        return result;
    }
}
