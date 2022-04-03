package br.com.cwi.crescer.tcc.jhoel.bagnasco.validators;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostExistsValidator {
    public void validate(Optional<Post> postOptional) {
        if (!postOptional.isPresent())
            throw new NotFoundException("Post does not exist");
    }
}
