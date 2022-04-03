package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserSignUpRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.request.AuthRequestMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.domain.UserMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.UserRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.AuthProvider;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserSignUpService {

    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private AuthRequestMapper authRequestMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public void signUp(UserSignUpRequest request) {
        AuthRequest authRequest = authRequestMapper.map(request);
        authRequest.setRoles(Arrays.asList(USER_ROLE));

        authProvider.register(authRequest);

        userRepository.save(userMapper.map(request));
    }
}
