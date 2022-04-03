package br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.FriendshipResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.FriendshipStatus;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.User;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.FriendshipResponseMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.mapper.response.UserResponseMapper;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.repository.FriendshipRepository;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainMyselfService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindDomainUserByEmailService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.validators.FriendshipAlreadyExistsValidator;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.validators.FriendshipSelfValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SendFriendshipRequestService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private FindDomainMyselfService findDomainMyselfService;

    @Autowired
    private FindDomainUserByEmailService findDomainUserByEmailService;

    @Autowired
    private FriendshipAlreadyExistsValidator friendshipAlreadyExistsValidator;

    @Autowired
    private FriendshipSelfValidator friendshipSelfValidator;

    @Autowired
    private FriendshipResponseMapper friendshipResponseMapper;

    @Autowired
    private UserResponseMapper userResponseMapper;

    public FriendshipResponse send(String userEmail) {

        User myself = findDomainMyselfService.find();
        User someone = findDomainUserByEmailService.find(userEmail);

        friendshipSelfValidator.validate(myself, someone);

        Optional<Friendship> myselfAsSenderOptional =
                friendshipRepository.findBySenderAndReceiver(myself, someone);

        friendshipAlreadyExistsValidator.validate(myselfAsSenderOptional);

        Optional<Friendship> someoneAsSenderOptional =
                friendshipRepository.findBySenderAndReceiver(someone, myself);

        friendshipAlreadyExistsValidator.validate(someoneAsSenderOptional);

        Friendship friendship = new Friendship();
        friendship.setSender(myself);
        friendship.setReceiver(someone);
        friendship.setStatus(FriendshipStatus.PENDING);
        friendship.setLastStatusUpdate(LocalDateTime.now());

        friendship = friendshipRepository.save(friendship);

        FriendshipResponse response = friendshipResponseMapper.map(
                friendship,
                userResponseMapper.map(friendship.getSender()),
                userResponseMapper.map(friendship.getReceiver())
                );

        return response;
    }

}

