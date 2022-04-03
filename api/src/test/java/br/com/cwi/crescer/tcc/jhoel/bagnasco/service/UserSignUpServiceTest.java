package br.com.cwi.crescer.tcc.jhoel.bagnasco.service;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserSignUpRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.domain.UserMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.request.AuthRequestMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.UserRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.AuthProvider;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.dto.AuthRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.UserSignUpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserSignUpServiceTest {

    @InjectMocks
    private UserSignUpService subject;

    @Mock
    private AuthProvider authProvider;

    @Mock
    private AuthRequestMapper authRequestMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSignUpRequest request;

    @Mock
    private AuthRequest authRequest;

    @Mock
    private User user;

    @Test
    public void shouldSaveUserWhenSignUp() {


        Mockito.when(authRequestMapper.map(request)).thenReturn(authRequest);
        Mockito.when(userMapper.map(request)).thenReturn(user);

        subject.signUp(request);

        Mockito.verify(authRequestMapper).map(request);
        Mockito.verify(authRequest).setRoles(Mockito.anyList());
        Mockito.verify(authProvider).register(authRequest);
        Mockito.verify(userRepository).save(user);
    }
}