package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.UserResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindUserByNameOrEmailService {

    @Autowired
    private FindDomainUserByNameOrEmail findDomainUserByNameOrEmail;

    @Autowired
    private UserResponseMapper userResponseMapper;

    public List<UserResponse> find(String query, String page) {
        List<User> userList = findDomainUserByNameOrEmail.find(query, page);

        return userList.stream()
                .map(userResponseMapper::map)
                .collect(Collectors.toList());
    }
}
