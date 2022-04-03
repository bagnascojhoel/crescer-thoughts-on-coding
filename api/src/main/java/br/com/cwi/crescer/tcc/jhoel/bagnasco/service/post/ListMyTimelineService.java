package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.post;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.PostResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.PostResponseMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListMyTimelineService {

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private ListDomainMyTimelineService listDomainMyTimelineService;

    @Autowired
    private PostResponseMapper postResponseMapper;

    @Autowired
    private MapCommentsResponseService mapCommentsResponseService;

    public List<PostResponse> list(String page) {
        User myself = findDomainMyselfService.find();

        return listDomainMyTimelineService.list(page).stream()
                .map(post -> postResponseMapper.map(
                        post,
                        mapCommentsResponseService.map(post.getComments(), myself),
                        myself)
                )
                .collect(Collectors.toList());
    }

}
