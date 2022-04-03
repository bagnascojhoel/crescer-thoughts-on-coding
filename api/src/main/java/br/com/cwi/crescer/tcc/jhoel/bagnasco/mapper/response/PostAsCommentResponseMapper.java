package br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.PostAsCommentResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import org.springframework.stereotype.Component;

@Component
public class PostAsCommentResponseMapper {


    public PostAsCommentResponse map(Post post, User myself) {
        PostAsCommentResponse result = new PostAsCommentResponse();

        result.setId(post.getId());
        result.setAuthorName(post.getAuthor().getNickname());
        result.setAuthorName(post.getAuthor().getName());
        result.setAuthorEmail(post.getAuthor().getEmail());
        result.setContent(post.getContent());
        result.setCreatedAt(post.getCreation());
        result.setVisibility(post.getVisibility());
        result.setLikes(post.getLikes().stream().count());
        result.setHasMyLike(post.getLikes().contains(myself));

        return result;
    }
}
