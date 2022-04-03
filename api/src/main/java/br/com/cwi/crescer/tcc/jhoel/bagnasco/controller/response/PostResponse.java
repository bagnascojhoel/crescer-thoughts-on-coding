package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.PostVisibility;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponse {

    private Long id;

    private String authorName;

    private String authorNickname;

    private String authorEmail;

    private List<PostAsCommentResponse> comments;

    private String content;

    private LocalDateTime createdAt;

    private PostVisibility visibility;

    private Long likes;

    private boolean hasMyLike;

}
