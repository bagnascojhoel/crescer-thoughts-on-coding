package br.com.cwi.crescer.tcc.jhoel.bagnasco.validators;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.exception.BusynessLogicException;
import org.springframework.stereotype.Component;

@Component
public class PostMyOwnershipValidator {
    public void validate(Post post, User myself) {
        if (!post.getAuthor().equals(myself))
            throw new BusynessLogicException("Cannot change someone else's post");
    }
}
