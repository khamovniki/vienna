package com.khamovniki.vienna.storage.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khamovniki.vienna.storage.dto.TagChangeRequestDto;
import com.khamovniki.vienna.storage.entity.Tag;
import com.khamovniki.vienna.storage.entity.TagRepository;
import com.khamovniki.vienna.storage.entity.User;
import com.khamovniki.vienna.storage.entity.UserRepository;
import com.khamovniki.vienna.storage.exception.ViennaDataException;
import com.khamovniki.vienna.storage.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Override
    @Transactional
    public void createUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        //not thread safe, but who cares
        if (!user.isPresent()) {
            User newUser = User.builder()
                    .userId(userId)
                    .build();
            userRepository.save(newUser);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> listTags(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ViennaDataException("User not found"));
        return user.getTags();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> listAbsentTags(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ViennaDataException("User not found"));
        Set<String> tags = tagRepository.findAll().stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
        tags.removeAll(user.getTags());
        return tags;
    }

    @Override
    @Transactional
    public void addUserTag(TagChangeRequestDto request) {
        long userId = request.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ViennaDataException("User not found"));

        Set<String> userTags = user.getTags();
        String newTag = request.getTag();
        validateTag(newTag);
        userTags.add(newTag);
        userRepository.save(user.withTags(userTags));
    }

    @Override
    public void removeUserTag(TagChangeRequestDto request) {
        long userId = request.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ViennaDataException("User not found"));

        Set<String> userTags = user.getTags();

        String oldTag = request.getTag();
        validateTag(oldTag);
        userTags.remove(oldTag);
        userRepository.save(user.withTags(userTags));
    }

    private void validateTag(String tag) {
        tagRepository.findById(tag).orElseThrow(() -> new ViennaDataException("Tag not found"));
    }
}
