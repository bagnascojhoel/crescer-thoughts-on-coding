package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.PostAsCommentResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.PostAsCommentResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapCommentsResponseService {

    @Autowired
    private PostAsCommentResponseMapper postAsCommentResponseMapper;

    public List<PostAsCommentResponse> map(List<Post> comments, User myself) {
        return comments.stream().map(c ->
                postAsCommentResponseMapper.map(c, myself)
        ).collect(Collectors.toList());
    }
}
