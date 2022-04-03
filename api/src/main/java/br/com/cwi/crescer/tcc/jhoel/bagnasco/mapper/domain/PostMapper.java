package br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.domain;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.PostCreateRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class PostMapper {

    public Post map(PostCreateRequest request, User author) {
        Post result = new Post();

        result.setAuthor(author);
        result.setContent(request.getContent());
        result.setCreation(LocalDateTime.now());
        result.setVisibility(request.getVisibility());
        result.setLikes(Arrays.asList());

        return result;
    }
}
