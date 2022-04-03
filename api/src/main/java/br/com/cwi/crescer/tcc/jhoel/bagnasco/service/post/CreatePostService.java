package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.PostCreateRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.PostResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.domain.PostMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.PostResponseMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.PostRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostResponseMapper postResponseMapper;

    public PostResponse create(PostCreateRequest request) {
        User myself = findDomainMyselfService.find();
        Post post = postMapper.map(request, myself);

        post = postRepository.save(post);

        return postResponseMapper.map(post, null, null);
    }
}
