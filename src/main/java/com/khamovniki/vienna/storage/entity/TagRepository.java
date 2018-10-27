package com.khamovniki.vienna.storage.entity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, String> {
    List<Tag> findAll();
}
