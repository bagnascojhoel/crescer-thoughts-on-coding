package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.UserRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.validators.UserDoesNotExistValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindDomainUserByEmailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDoesNotExistValidator userDoesNotExistValidator;

    public User find(String email) {
        Optional<User> userList = userRepository.findByEmail(email);

        userDoesNotExistValidator.validate(userList);

        return  userList.get();
    }
}
