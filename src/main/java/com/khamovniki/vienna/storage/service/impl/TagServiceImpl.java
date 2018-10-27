package com.khamovniki.vienna.storage.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.khamovniki.vienna.storage.entity.Tag;
import com.khamovniki.vienna.storage.entity.TagRepository;
import com.khamovniki.vienna.storage.service.TagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Set<String> listTags() {
        return tagRepository.findAll().stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
    }
}
