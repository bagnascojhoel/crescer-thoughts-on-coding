package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.UserResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserByEmailService {

    @Autowired
    private FindDomainUserByEmailService findDomainUserByEmailService;

    @Autowired
    private UserResponseMapper mapper;

    public UserResponse find(String email) {
        return mapper.map(findDomainUserByEmailService.find(email));
    }
}
