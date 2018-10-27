package com.khamovniki.vienna.storage.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostRequestDto {

    private final String message;
    private final Set<String> tags;
}