package com.khamovniki.vienna.storage.service.impl;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.khamovniki.vienna.storage.dto.PostRequestDto;
import com.khamovniki.vienna.storage.entity.TagRepository;
import com.khamovniki.vienna.storage.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final TagRepository tagRepository;
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public void createPost(PostRequestDto request) {
//        request.getTags().stream()
//                .map(tag -> tagRepository.findById(tag).orElseThrow(() -> new ViennaDataException("Tag not found")))
//                .map(Tag::getUsers)
//                ;
//        threadPoolTaskScheduler.execute(new SendMessageTask(request.getMessage(),));
    }

    private class SendMessageTask implements Runnable{

        private final long postId;

        SendMessageTask(long postId) {
            this.postId = postId;
        }

        @Override
        public void run() {

        }
    }
}
