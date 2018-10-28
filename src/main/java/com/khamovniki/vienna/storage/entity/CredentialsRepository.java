package com.khamovniki.vienna.storage.entity;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CredentialsRepository extends CrudRepository<Credentials, String> {
    Optional<Credentials> findByToken(String token);
}
