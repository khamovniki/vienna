package com.khamovniki.vienna.storage.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelegramPostRequestDto {

    private final String message;
    private final Set<Long> users;
}
