package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.PostRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.validators.PostExistsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindDomainPostByIdService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostExistsValidator postExistsValidator;

    public Post find(String id) {
        Optional<Post> post = postRepository.findById(Long.parseLong(id));

        postExistsValidator.validate(post);

        return post.get();
    }
}
