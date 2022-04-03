package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.UserRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.validators.UserDoesNotExistValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindDomainMyselfService {

    @Autowired
    private EvaluateTokenService evaluateTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDoesNotExistValidator userDoesNotExistValidator;

    public User find() {
        User partialSelf = evaluateTokenService.evaluate();

        Optional<User> myself = userRepository.findByEmail(partialSelf.getEmail());

        userDoesNotExistValidator.validate(myself);

        return myself.get();
    }
}
