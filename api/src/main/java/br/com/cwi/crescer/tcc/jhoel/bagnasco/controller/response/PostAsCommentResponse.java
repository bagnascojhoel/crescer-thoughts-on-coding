package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.PostVisibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostAsCommentResponse {

    private Long id;

    private String authorName;

    private String authorNickname;

    private String authorEmail;

    private String content;

    private LocalDateTime createdAt;

    private PostVisibility visibility;

    private Long likes;

    private boolean hasMyLike;

}
