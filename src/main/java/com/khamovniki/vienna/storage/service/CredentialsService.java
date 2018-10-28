package com.khamovniki.vienna.storage.service;

import com.khamovniki.vienna.storage.dto.CredentialsDto;

public interface CredentialsService {

    void signUp(CredentialsDto credentials);
    String signIn(CredentialsDto credentials);
}
