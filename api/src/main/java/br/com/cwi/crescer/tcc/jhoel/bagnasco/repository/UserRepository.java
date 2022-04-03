package br.com.cwi.crescer.tcc.jhoel.bagnasco.repository;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User save(User user);

    Optional<User> findByEmail(String email);

    List<User> findAllByNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(String name, String email, Pageable pageable);

}
