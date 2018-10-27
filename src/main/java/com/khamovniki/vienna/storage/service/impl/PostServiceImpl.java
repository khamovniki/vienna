package com.khamovniki.vienna.storage.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khamovniki.vienna.storage.dto.PostRequestDto;
import com.khamovniki.vienna.storage.entity.PostMessageTask;
import com.khamovniki.vienna.storage.entity.PostMessageTaskRepository;
import com.khamovniki.vienna.storage.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMessageTaskRepository postMessageTaskRepository;

    @Override
    @Transactional
    public void createPost(PostRequestDto request) {
        PostMessageTask task = PostMessageTask.builder()
                .message(request.getMessage())
                .tags(request.getTags())
                .timestamp(request.getTimestamp())
                .build();
        postMessageTaskRepository.save(task);
    }
}
