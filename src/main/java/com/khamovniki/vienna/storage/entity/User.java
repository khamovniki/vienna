package com.khamovniki.vienna.storage.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.khamovniki.vienna.storage.entity.converter.StringSetConverter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Entity
@Data
@Builder
@Wither
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class User {

    @Id
    private long userId;
    @Lob
    @Convert(converter = StringSetConverter.class)
    @Builder.Default
    private Set<String> tags = new HashSet<>();
}
