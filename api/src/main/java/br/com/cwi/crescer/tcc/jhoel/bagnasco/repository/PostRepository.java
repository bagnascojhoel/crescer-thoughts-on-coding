package br.com.cwi.crescer.tcc.jhoel.bagnasco.repository;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Post;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.PostVisibility;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    Post save(Post post);

    Optional<Post> findById(Long id);

    List<Post> findAllByAuthor(User author, Pageable pageable);

    List<Post> findAllByAuthorEmail(String email, Pageable pageable);

    List<Post> findAllByAuthorEmailAndVisibility(String email, PostVisibility visibility, Pageable pageable);

    boolean existsByAuthorIdAndCreationAfter(Long authorId, LocalDateTime createdAt);

    List<Post> findAllByAuthorIdAndCreationAfter(Long authorId, LocalDateTime createdAt);

}
