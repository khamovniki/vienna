package com.khamovniki.vienna.storage.service;

import java.util.Set;

import com.khamovniki.vienna.storage.dto.TagChangeRequestDto;

public interface UserService {
    void createUser(long userId);

    Set<String> listUserTags(long userId);

    void addUserTag(TagChangeRequestDto request);

    void removeUserTag(TagChangeRequestDto request);
}
