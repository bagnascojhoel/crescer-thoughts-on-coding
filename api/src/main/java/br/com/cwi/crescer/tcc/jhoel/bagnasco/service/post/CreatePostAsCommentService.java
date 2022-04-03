package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.PostCreateRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.domain.PostMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.PostRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePostAsCommentService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private FindDomainPostByIdService findDomainPostByIdService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostRepository postRepository;

    public void create(String postId, PostCreateRequest request) {
        User myself = findDomainMyselfService.find();
        Post post = findDomainPostByIdService.find(postId);
        Post comment = postMapper.map(request, myself);

        comment.setCommentTo(post);

        postRepository.save(comment);
    }
}
