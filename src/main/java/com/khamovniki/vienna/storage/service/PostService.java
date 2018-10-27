package com.khamovniki.vienna.storage.service;

import com.khamovniki.vienna.storage.dto.PostRequestDto;

public interface PostService {
    void createPost(PostRequestDto request);
}
