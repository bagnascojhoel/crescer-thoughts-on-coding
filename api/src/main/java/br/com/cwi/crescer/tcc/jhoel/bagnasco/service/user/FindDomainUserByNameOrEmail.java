package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindDomainUserByNameOrEmail {

    private static final int ITENS_PER_PAGE = 10;

    @Autowired
    private UserRepository userRepository;

    public List<User> find(String query, String page) {
        Pageable pageRequest = PageRequest.of(
                Integer.parseInt(page),
                ITENS_PER_PAGE,
                Sort.by("name").descending().and(Sort.by("email").descending())
        );

        return userRepository.findAllByNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(query, query, pageRequest);
    }
}
