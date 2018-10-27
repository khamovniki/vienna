package com.khamovniki.vienna.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TagChangeRequestDto {

    private final long userId;
    private final String tag;
}
