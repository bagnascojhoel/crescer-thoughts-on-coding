package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.PostCreateRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.PostResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.PostVisibility;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.LikePostService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.UnlikePostService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/private/post")
public class PostController {

    @Autowired
    private CreatePostService createPostService;

    @Autowired
    private CreatePostAsCommentService createPostAsCommentService;

    @Autowired
    private UpdateVisibilityService updateVisibilityService;

    @Autowired
    private ListMyTimelineService listMyTimelineService;

    @Autowired
    private ListTimelineService listTimelineService;

    @Autowired
    private LikePostService likePostService;

    @Autowired
    private UnlikePostService unlikePostService;

    @PostMapping
    public PostResponse createPost(@RequestBody @Valid PostCreateRequest postCreateRequest) {
        return createPostService.create(postCreateRequest);
    }

    @PostMapping("{postId}")
    public void commentOnPost(
            @PathVariable(name = "postId") String postId,
            @RequestBody @Valid PostCreateRequest request) {
        createPostAsCommentService.create(postId, request);
    }

    @PostMapping("update-visibility/{postId}")
    public void updateVisibility(
            @PathVariable(name = "postId") String postId,
            @RequestParam(name = "visibility") PostVisibility visibility) {
        updateVisibilityService.update(postId, visibility);
    }

    @GetMapping
    public List<PostResponse> listMyTimeline(@RequestParam(name = "page") String page) {
        return listMyTimelineService.list(page);
    }

    @GetMapping("{email}")
    public List<PostResponse> listTimeline(
            @PathVariable(name = "email") String email,
            @RequestParam(name = "page") String page) {

        return listTimelineService.list(email, page);
    }

    @PostMapping("like/{postId}")
    public void likePost(@PathVariable(name = "postId") String postId) {
        likePostService.like(postId);
    }

    @PostMapping("unlike/{postId}")
    public void unlikePost(@PathVariable(name = "postId") String postId) {
        unlikePostService.unlike(postId);
    }


}
