package com.khamovniki.vienna.storage.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.khamovniki.vienna.storage.dto.TagChangeRequestDto;
import com.khamovniki.vienna.storage.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void createUser(String userId) {

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
