package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.PartialUpdateUserRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindUserByEmailService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindUserByNameOrEmailService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.FindMyselfService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.UpdateMyselfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/private/user")
public class UserController {

    @Autowired
    private FindMyselfService findMyselfService;

    @Autowired
    private UpdateMyselfService updateMyselfService;

    @Autowired
    private FindUserByNameOrEmailService findUserByNameOrEmailService;

    @Autowired
    private FindUserByEmailService findUserByEmailService;

    @GetMapping
    public UserResponse findMyself() {
        return findMyselfService.find();
    }

    @PostMapping("edit")
    public void updateMyself(@RequestBody @Valid PartialUpdateUserRequest request) {
        updateMyselfService.update(request);
    }

    @GetMapping("search")
    public List<UserResponse> findUsersByNameOrEmail(@RequestParam(name = "query") String query, @RequestParam(name = "page", defaultValue = "0") String page) {
        return findUserByNameOrEmailService.find(query, page);
    }

    @GetMapping("{email}")
    public UserResponse findUserByEmail(@PathVariable String email) {
        return findUserByEmailService.find(email);
    }
}
