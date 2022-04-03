package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.UserResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindMyselfService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private UserResponseMapper userResponseMapper;

    public UserResponse find() {
        User myself = findDomainMyselfService.find();

        return userResponseMapper.map(myself);
    }
}
