package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserLoginRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserLoginResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.request.AuthRequestMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private AuthRequestMapper authRequestMapper;

    public UserLoginResponse login(UserLoginRequest request) {
        String token = authProvider.login(request).getToken();

        return new UserLoginResponse(request.getEmail(), token);
    }
}
