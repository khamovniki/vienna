package com.khamovniki.vienna.storage.service.impl;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.khamovniki.vienna.storage.dto.PostRequestDto;
import com.khamovniki.vienna.storage.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public void createPost(PostRequestDto request) {
        ;
    }

    private class SendMessageTask {

    }
}
