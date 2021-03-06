package com.khamovniki.vienna.storage.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
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

    private static final int MAGIC_DENOMINATOR = 2;
    private static final int MAGIC_NUMERATOR = 1;

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
        return user.listTagsNames();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> listAbsentTags(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ViennaDataException("User not found"));
        Set<String> tags = tagRepository.findAll().stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
        tags.removeAll(user.listTagsNames());
        return tags;
    }

    @Override
    @Transactional
    public void addUserTag(TagChangeRequestDto request) {
        long userId = request.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ViennaDataException("User not found"));
        Tag tag = tagRepository.findById(request.getTag()).orElseThrow(() -> new ViennaDataException("Tag not found"));
        Set<Tag> userTags = user.getTags();

        userTags.add(tag);
    }

    @Override
    @Transactional
    public void removeUserTag(TagChangeRequestDto request) {
        long userId = request.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ViennaDataException("User not found"));
        Tag tag = tagRepository.findById(request.getTag()).orElseThrow(() -> new ViennaDataException("Tag not found"));

        Set<Tag> userTags = user.getTags();
        userTags.remove(tag);
    }


    @Override
    @Transactional(readOnly = true)
    public Set<String> recommend(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ViennaDataException("User not found"));
        Set<Tag> userTags = user.getTags();
        int min = userTags.size() / MAGIC_DENOMINATOR * MAGIC_NUMERATOR;
        Map<String, Long> tagsMap = userTags.stream()
                .map(Tag::getUsers)
                .flatMap(Collection::stream)
                .distinct()
                .filter(u -> u.getUserId() != userId)
                .filter(u -> u.getTags().stream()
                        .filter(userTags::contains)
                        .count() >= min)
                .map(User::getTags)
                .flatMap(Collection::stream)
                .filter(tag -> !userTags.contains(tag))
                .collect(Collectors.groupingBy(Tag::getName, Collectors.counting()));

        return tagsMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
