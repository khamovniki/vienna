package com.khamovniki.vienna.storage.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.khamovniki.vienna.storage.dto.TagChangeRequestDto;
import com.khamovniki.vienna.storage.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    @Override
    public Set<String> listTags() {
        return null;
    }

    @Override
    public Set<String> listUserTags(String userId) {
        return null;
    }

    @Override
    public void addUserTag(TagChangeRequestDto request) {
    }

    @Override
    public void removeUserTag(TagChangeRequestDto request) {

    }
}
