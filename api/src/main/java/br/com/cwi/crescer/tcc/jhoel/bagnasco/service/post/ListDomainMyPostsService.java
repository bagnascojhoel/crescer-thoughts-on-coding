package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.PostRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListDomainMyPostsService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private PostRepository postRepository;

    public List<Post> list(String page, int postsQty) {
        User myself = findDomainMyselfService.find();
        Pageable pageRequest = PageRequest.of(
                Integer.parseInt(page),
                postsQty,
                Sort.by("creation").descending()
        );

        return postRepository.findAllByAuthor(myself, pageRequest);
    }
}
