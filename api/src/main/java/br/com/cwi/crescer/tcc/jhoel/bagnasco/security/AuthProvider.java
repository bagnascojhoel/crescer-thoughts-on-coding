package br.com.cwi.crescer.tcc.jhoel.bagnasco.security;

import br.com.cwi.crescer.tcc.jhoel.bagnasco.controller.request.UserLoginRequest;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.exception.BusynessLogicException;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.exception.NotAllowedException;
import br.com.cwi.crescer.tcc.jhoel.bagnasco.security.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class AuthProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final String BASE_URL = "http://52.191.131.0:3000";

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException { }

    public void register(AuthRequest user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<AuthRequest> request = new HttpEntity(user, headers);

        try {
            ResponseEntity<AuthMeResponse> response = new RestTemplate().postForEntity(
                    BASE_URL + "/register",
                    request,
                    AuthMeResponse.class
            );

        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {

                try {
                    AuthException authException = new ObjectMapper().readValue(exception.getResponseBodyAsString(), AuthException.class);
                    throw new BusynessLogicException(authException.getMessage());
                } catch (JsonProcessingException e) {
                    throw new BusynessLogicException("Couldn't register.");
                }
            } else {
                throw new RuntimeException(exception);
            }

        }
    }

    public AuthLoginResponse login(UserLoginRequest user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<UserLoginRequest> request = new HttpEntity(user, headers);

        try {
            ResponseEntity<AuthLoginResponse> response = new RestTemplate().postForEntity(
                    BASE_URL + "/login",
                    request,
                    AuthLoginResponse.class
            );

            return response.getBody();

        } catch (HttpClientErrorException exception) {

            if (exception.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {

                try {
                    AuthException authException = new ObjectMapper().readValue(exception.getResponseBodyAsString(), AuthException.class);
                    throw new BusynessLogicException(authException.getMessage());

                } catch (JsonProcessingException e) {
                    throw new BusynessLogicException("Couldn't login.");
                }

            } else {
                throw new RuntimeException(exception);
            }
        }
    }

    @Override
    protected UserDetails retrieveUser(String token, UsernamePasswordAuthenticationToken _)
            throws AuthenticationException {

        if (token == null) new UsernameNotFoundException("User not found.");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        headers.set("Authorization", token);

        HttpEntity request = new HttpEntity(headers);

        try {

            ResponseEntity<AuthMeResponse> response = new RestTemplate().exchange(
                    BASE_URL + "/me",
                    HttpMethod.GET,
                    request,
                    AuthMeResponse.class
            );

            AuthMeResponse authMeResponse = response.getBody();

            return new AuthUserDetailsResponse(
                    authMeResponse.getEmail(),
                    authMeResponse.getFirstName(),
                    authMeResponse.getLastName(),
                    authMeResponse.getRoles()
            );

        } catch (Exception exception) {

            throw new NotAllowedException("User not authorized.");
        }

    }
}
