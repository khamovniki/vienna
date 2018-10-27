package com.khamovniki.vienna.storage.service;

import java.util.Set;

import com.khamovniki.vienna.storage.dto.TagChangeRequestDto;

public interface TagService {
    Set<String> listTags();

    Set<String> listUserTags(String userId);

    void addUserTag(TagChangeRequestDto request);

    void removeUserTag(TagChangeRequestDto request);
}
