package com.khamovniki.vienna.storage.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khamovniki.vienna.storage.dto.CredentialsDto;
import com.khamovniki.vienna.storage.entity.Credentials;
import com.khamovniki.vienna.storage.entity.CredentialsRepository;
import com.khamovniki.vienna.storage.exception.ViennaDataException;
import com.khamovniki.vienna.storage.service.CredentialsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialsService {

    private final CredentialsRepository credentialsRepository;

    @Override
    @Transactional
    public void signUp(CredentialsDto credentials) {
        Credentials savedCredentials = Credentials.builder()
                .login(credentials.getLogin())
                .password(credentials.getPassword())
                .token(credentials.getLogin())
                .build();
        credentialsRepository.save(savedCredentials);
    }

    @Override
    @Transactional(readOnly = true)
    public String signIn(CredentialsDto credentials) {
        Credentials user = credentialsRepository.findById(credentials.getLogin()).orElseThrow(() -> new ViennaDataException("No such user"));
        if (!credentials.getPassword().equals(user.getPassword())) {
            throw new SecurityException();
        }
        return user.getToken();
    }
}
