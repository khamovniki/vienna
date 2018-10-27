package com.khamovniki.vienna.storage.service;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

public interface TagService {
    Set<String> listTags();

    @Transactional
    void createTag(String tagName);
}
