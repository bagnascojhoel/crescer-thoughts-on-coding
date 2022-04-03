package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.PostVisibility;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.PostRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.validators.PostMyOwnershipValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateVisibilityService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private FindDomainPostByIdService findDomainPostByIdService;

    @Autowired
    private PostMyOwnershipValidator postMyOwnershipValidator;

    @Autowired
    private PostRepository postRepository;

    public void update(String postId, PostVisibility visibility) {
        User myself = findDomainMyselfService.find();
        Post post = findDomainPostByIdService.find(postId);

        postMyOwnershipValidator.validate(post, myself);

        post.setVisibility(visibility);

        postRepository.save(post);
    }
}
