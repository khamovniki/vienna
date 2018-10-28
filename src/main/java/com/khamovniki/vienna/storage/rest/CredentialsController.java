package com.khamovniki.vienna.storage.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khamovniki.vienna.storage.dto.CredentialsDto;
import com.khamovniki.vienna.storage.service.CredentialsService;

import lombok.RequiredArgsConstructor;

import static com.khamovniki.vienna.ViennaApplication.APPLICATION_NAME;

@RestController
@RequestMapping(APPLICATION_NAME)
@RequiredArgsConstructor
public class CredentialsController {

    private final CredentialsService credentialsService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody CredentialsDto credentials) {
        credentialsService.signUp(credentials);
    }

    @PostMapping("/signIn")
    public String signIn(@RequestBody CredentialsDto credentials) {
        return credentialsService.signIn(credentials);
    }
}
