package br.com.cwi.crescer.tcc.jhoel.bagnasco.service;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserLoginRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserLoginResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.request.AuthRequestMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.AuthProvider;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.dto.AuthLoginResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.UserLoginService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginServiceTest {

    @InjectMocks
    private UserLoginService subject;

    @Mock
    private AuthProvider authProvider;

    @Mock
    private AuthRequestMapper authRequestMapper;

    @Mock
    private AuthLoginResponse authLoginResponse;

    @Test
    public void shouldFindATokenWhenLogin() {
        String token = "token";
        String email = "example@domain.com";
        UserLoginRequest request = new UserLoginRequest(
                email,
                "IAmThePassword123"
        );

        UserLoginResponse expected = new UserLoginResponse(email, token);

        Mockito.when(authProvider.login(request)).thenReturn(authLoginResponse);
        Mockito.when(authLoginResponse.getToken()).thenReturn(token);

        UserLoginResponse actual = subject.login(request);

        Mockito.verify(authProvider).login(request);
        Mockito.verify(authLoginResponse).getToken();

        Assert.assertEquals(expected, actual);
    }
}