package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.UserResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListMyFriendsService {

    @Autowired
    private ListDomainMyFriendsService listDomainMyFriendsService;

    @Autowired
    private UserResponseMapper mapper;

    public List<UserResponse> list() {

        return listDomainMyFriendsService.list().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }
}
