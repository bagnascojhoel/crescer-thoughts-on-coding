package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.PostRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship.ListDomainMyFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ListDomainMyTimelineService {

    private static final int ITEMS_PER_PAGE = 5;

    @Autowired
    private ListDomainMyPostsService listDomainMyPostsService;

    @Autowired
    private ListDomainMyFriendsService listDomainMyFriendsService;

    @Autowired
    private PostRepository postRepository;

    public List<Post> list(String page) {
        List<Post> myPosts = listDomainMyPostsService.list(page, ITEMS_PER_PAGE);
        List<User> myFriends = listDomainMyFriendsService.list();

        Pageable pageRequest = PageRequest.of(
                Integer.parseInt(page),
                ITEMS_PER_PAGE,
                Sort.by("creation").descending()
        );

        List<Post> friendsPosts = myFriends.stream()
                .map(f -> postRepository.findAllByAuthor(f, pageRequest))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


        List<Post> posts = Stream.concat(myPosts.stream(), friendsPosts.stream()).collect(Collectors.toList()).stream()
                .filter(p -> p.getCommentTo() == null)
                .sorted((p1, p2) -> p1.getCreation().compareTo(p2.getCreation()))
                .collect(Collectors.toList());

        Collections.reverse(posts);

        return posts;
    }
}
