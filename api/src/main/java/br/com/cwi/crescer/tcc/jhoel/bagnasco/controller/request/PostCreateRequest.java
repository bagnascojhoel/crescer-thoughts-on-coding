package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.PostVisibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostCreateRequest {

    @NotNull(message = "Content cannot be null")
    @NotEmpty(message = "Content cannot be empty")
    @Size(max = 280, message = "Content must have at most 280 characters")
    private String content;

    @NotNull(message = "Visibility cannot be null")
    private PostVisibility visibility;

}
