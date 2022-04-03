package br.com.cwi.crescer.tcc.jhoel.bagnasco.service;


import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.PostRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post.FindDomainPostByIdService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnlikePostService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private FindDomainPostByIdService findDomainPostByIdService;

    @Autowired
    private PostRepository postRepository;

    public void unlike(String postId) {
        User myself = findDomainMyselfService.find();
        Post post = findDomainPostByIdService.find(postId);
        List<User> likes = post.getLikes();
        likes.remove(myself);
        post.setLikes(likes);

        Post result = postRepository.save(post);

    }
}
