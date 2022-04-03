package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.FriendshipRequestResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.FriendshipResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.domain.Friendship;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.friendship.*;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/friendship")
public class FriendshipController {

    @Autowired
    private SendFriendshipRequestService sendFriendshipRequestService;

    @Autowired
    private ListMyFriendshipRequestsService listMyFriendshipRequestsService;

    @Autowired
    private AcceptFriendshipRequestService acceptFriendshipRequestService;

    @Autowired
    private ListMyFriendsService listMyFriendsService;

    @Autowired
    private DenyFriendshipRequestService denyFriendshipRequestService;

    @Autowired
    private FindFriendshipService findFriendshipService;

    @Autowired
    private UndoFriendshipService undoFriendshipService;

    @GetMapping
    public List<UserResponse> listMyFriends() {
        return listMyFriendsService.list();
    }

    @GetMapping("{email}")
    public FriendshipResponse findFriendship(@PathVariable("email") String email) {
        return findFriendshipService.find(email);
    }

    @GetMapping("requests")
    public List<FriendshipRequestResponse> listMyFriendshipRequests() {
        return listMyFriendshipRequestsService.list();
    }

    @PostMapping("requests/{receiver}")
    public FriendshipResponse sendRequest(@PathVariable("receiver") String receiverEmail) {
        return sendFriendshipRequestService.send(receiverEmail);
    }

    @PostMapping("requests/accept/{id}")
    public void acceptFriendshipById(@PathVariable("id") String friendshipId) {
        acceptFriendshipRequestService.accept(friendshipId);
    }

    @PostMapping("requests/deny/{id}")
    public void denyFriendshipById(@PathVariable("id") String friendshipId) {
        denyFriendshipRequestService.deny(friendshipId);
    }

    @PostMapping("undo/{email}")
    public void undoFriendship(@PathVariable("email") String email) {
        undoFriendshipService.undo(email);
    }

}
