package br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    @Autowired
    private ModelMapper mapper;

    public UserResponse map(User user) {
        UserResponse result = mapper.map(user, UserResponse.class);

        return result;
    }
}
