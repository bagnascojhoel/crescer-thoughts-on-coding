package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.PostResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.PostVisibility;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.PostResponseMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.PostRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListTimelineService {

    private static final int ITEMS_PER_PAGE = 10;

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private CheckFriendshipExistsService checkFriendshipExistsService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostResponseMapper postResponseMapper;

    @Autowired
    private MapCommentsResponseService mapCommentsResponseService;

    public List<PostResponse> list(String email, String page) {
        User myself = findDomainMyselfService.find();
        List<Post> posts = getPosts(email, page, checkFriendshipExistsService.check(email)).stream()
                .filter(p -> p.getCommentTo() == null)
                .sorted((p1, p2) -> p1.getCreation().compareTo(p2.getCreation()))
                .collect(Collectors.toList());

        Collections.reverse(posts);

        return posts.stream()
                .map(post -> postResponseMapper.map(
                        post,
                        mapCommentsResponseService.map(post.getComments(), myself),
                        myself)
                ).collect(Collectors.toList());
    }

    public List<Post> getPosts(String email, String page, boolean isFriend) {
        Pageable pageRequest = PageRequest.of(
                Integer.parseInt(page),
                ITEMS_PER_PAGE,
                Sort.by("creation").descending()
        );

        return isFriend ? postRepository.findAllByAuthorEmail(email, pageRequest)
                : postRepository.findAllByAuthorEmailAndVisibility(email, PostVisibility.PUBLIC, pageRequest);
    }

}
