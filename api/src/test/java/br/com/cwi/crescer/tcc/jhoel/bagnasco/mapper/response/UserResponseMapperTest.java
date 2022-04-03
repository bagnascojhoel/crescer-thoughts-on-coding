package br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserResponseMapperTest {

    @InjectMocks
    private UserResponseMapper subject;

    @Mock
    private ModelMapper mapper;

    @Test
    public void shouldMapToUserResponseCorrectly() {
        Long id = Long.parseLong("1");
        String name = "Jonas";
        String email = "email@domain.com";
        LocalDate birthDate = LocalDate.parse("2000-12-12");
        String nickname = null;
        String profilePhoto = "url";

        User user = new User(id, name, email, birthDate, nickname, profilePhoto);
        UserResponse userResponse = new UserResponse(id, name, email, birthDate, nickname, profilePhoto);

        Mockito.when(mapper.map(user, UserResponse.class)).thenReturn(userResponse);

        UserResponse actual = subject.map(user);

        Assert.assertEquals(name, actual.getName());
        Assert.assertEquals(email, actual.getEmail());
        Assert.assertEquals(birthDate, actual.getBirthDate());
        Assert.assertEquals(nickname, actual.getNickname());
        Assert.assertEquals(profilePhoto, actual.getProfilePhoto());

    }
}