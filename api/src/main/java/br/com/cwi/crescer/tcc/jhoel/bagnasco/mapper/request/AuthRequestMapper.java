package br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.request;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserLoginRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserSignUpRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.dto.AuthRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthRequestMapper {
    public AuthRequest map(UserSignUpRequest request) {
        AuthRequest result = new AuthRequest();

        result.setEmail(request.getEmail());
        result.setFirstName(request.getName());
        result.setPassword(request.getPassword());

        return result;
    }

    public AuthRequest map(UserLoginRequest request) {
        AuthRequest result = new AuthRequest();

        result.setEmail(request.getEmail());
        result.setPassword(request.getPassword());

        return result;
    }
}
