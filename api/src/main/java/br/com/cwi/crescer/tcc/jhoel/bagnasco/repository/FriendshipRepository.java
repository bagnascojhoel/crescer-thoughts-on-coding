package br.com.cwi.crescer.tcc.jhoel.bagnasco.repository;


import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.FriendshipStatus;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends Repository<Friendship, Long> {

    Friendship save(Friendship friendship);

    Optional<Friendship> findBySenderAndReceiver(User sender, User receiver);

    Optional<Friendship> findBySenderEmailAndReceiverEmail(String senderEmail, String receiverEmail);

    @Query("select f from Friendship f where f.sender.email = ?1 and f.receiver.email = ?2 and f.status = ?3 ")
    Optional<Friendship> findBySenderEmailAndReceiverEmailAndStatus(String senderEmail, String receiverEmail, FriendshipStatus status);

    List<Friendship> findByReceiverAndStatus(User receiver, FriendshipStatus status);

    @Query("select f from Friendship f where (f.sender.email = ?1 or f.receiver.email = ?2) and f.status = ?3 ")
    List<Friendship> findBySenderEmailOrReceiverEmailAndStatus(String senderEmail, String receiverEmail, FriendshipStatus status);

    Optional<Friendship> findById(Long id);

    void deleteById(Long id);
}
