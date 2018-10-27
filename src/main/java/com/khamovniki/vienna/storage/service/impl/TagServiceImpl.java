package com.khamovniki.vienna.storage.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableSet;
import com.khamovniki.vienna.storage.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    @Override
    public Set<String> listTags() {
        return ImmutableSet.of("volkovskaya", "vasileostrovskoe");
    }
}
