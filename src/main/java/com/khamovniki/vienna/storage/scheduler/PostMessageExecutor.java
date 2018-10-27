package com.khamovniki.vienna.storage.scheduler;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.khamovniki.vienna.storage.dto.TelegramPostRequestDto;
import com.khamovniki.vienna.storage.entity.PostMessageTask;
import com.khamovniki.vienna.storage.entity.PostMessageTaskRepository;
import com.khamovniki.vienna.storage.entity.Tag;
import com.khamovniki.vienna.storage.entity.TagRepository;
import com.khamovniki.vienna.storage.entity.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostMessageExecutor {

    private final PostMessageTaskRepository postMessageTaskRepository;
    private final TagRepository tagRepository;
    private final RestTemplate botRestTemplate;

    @Scheduled(fixedRate = 5_000L)
    @Transactional(readOnly = true)
    private void sendScheduled() {
        List<PostMessageTask> tasks = postMessageTaskRepository.findByTimestampLessThan(Instant.now());
        tasks.forEach(task -> {
            Hibernate.initialize(task.getTags());
            Set<Long> users = task.getTags().stream()
                    .map(tag -> tagRepository.findById(tag).map(Tag::getUsers).orElse(Collections.emptySet()))
                    .flatMap(Collection::stream)
                    .map(User::getUserId)
                    .collect(Collectors.toSet());
            TelegramPostRequestDto request = TelegramPostRequestDto.builder()
                    .message(task.getMessage())
                    .users(users)
                    .build();
            botRestTemplate.postForEntity("/sendPost", request, ResponseEntity.class);
        });
    }
}
