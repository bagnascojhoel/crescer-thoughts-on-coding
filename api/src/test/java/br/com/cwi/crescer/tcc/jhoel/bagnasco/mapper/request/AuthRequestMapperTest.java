package br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.request;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserLoginRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserSignUpRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthRequestMapperTest {

    @InjectMocks
    private AuthRequestMapper subject;

    @Mock
    private UserSignUpRequest userSignUpRequest;

    @Mock
    private UserLoginRequest userLoginRequest;

    @Test
    public void shouldMapToAuthRequestFromUserSignUpRequestCorrectly() {
        subject.map(userSignUpRequest);

        Mockito.verify(userSignUpRequest).getEmail();
        Mockito.verify(userSignUpRequest).getName();
        Mockito.verify(userSignUpRequest).getPassword();
    }

    @Test
    public void shouldMapToAuthRequestFromUserLoginRequestCorrectly() {
        subject.map(userLoginRequest);

        Mockito.verify(userLoginRequest).getEmail();
        Mockito.verify(userLoginRequest).getPassword();
    }
}