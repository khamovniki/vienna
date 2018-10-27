package com.khamovniki.vienna.storage.dto;

import java.time.Instant;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class PostRequestDto {

    private String message;
    private Set<String> tags;
    private Instant timestamp;
}
