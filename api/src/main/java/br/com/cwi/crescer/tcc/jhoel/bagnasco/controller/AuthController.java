package br.com.cwi.crescer.tcc.jhoel.bagnasco.controller;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserLoginRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserSignUpRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.response.UserLoginResponse;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.UserLoginService;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.service.user.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/public/auth")
public class AuthController {

    @Autowired
    private UserSignUpService userSignUpService;

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("sign-up")
    public void signUp(@RequestBody @Valid UserSignUpRequest request) {
        userSignUpService.signUp(request);
    }

    @PostMapping("login")
    public UserLoginResponse login(@RequestBody @Valid UserLoginRequest request) {
        return userLoginService.login(request);
    }
}
