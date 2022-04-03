package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.domain.UserMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.dto.AuthUserDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EvaluateTokenService {

    @Autowired
    private UserMapper userMapper;

    public User evaluate() {
        AuthUserDetailsResponse userDetails = (AuthUserDetailsResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userMapper.map(userDetails);
    }
}
