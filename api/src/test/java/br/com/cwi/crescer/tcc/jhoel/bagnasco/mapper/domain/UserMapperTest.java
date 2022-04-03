package br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.domain;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserSignUpRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    @InjectMocks
    private UserMapper subject;

    @Mock
    private UserSignUpRequest request;

    @Test
    public void shouldMapToUserCorrectly(){
        subject.map(request);

        Mockito.verify(request).getName();
        Mockito.verify(request).getEmail();
        Mockito.verify(request).getBirthDate();
        Mockito.verify(request).getNickname();
        Mockito.verify(request).getProfilePhoto();
        Mockito.verify(request, Mockito.never()).getPassword();
    }

}