package com.khamovniki.vienna.storage.entity;

import java.time.Instant;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostMessageTaskRepository extends CrudRepository<PostMessageTask, Long> {
    List<PostMessageTask> findByTimestampLessThan(Instant timestamp);
}
