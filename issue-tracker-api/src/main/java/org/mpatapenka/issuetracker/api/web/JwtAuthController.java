package org.mpatapenka.issuetracker.api.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mpatapenka.issuetracker.api.dto.JwtAuthRequest;
import org.mpatapenka.issuetracker.api.dto.JwtAuthResponse;
import org.mpatapenka.issuetracker.api.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class JwtAuthController {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;


    @PostMapping
    public JwtAuthResponse createAuthenticationToken(@Valid @RequestBody JwtAuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        log.debug("authentication: {}", authentication);
        return JwtAuthResponse.from(tokenProvider.createToken(authentication));
    }

    @GetMapping
    public void refresh() {

    }
}