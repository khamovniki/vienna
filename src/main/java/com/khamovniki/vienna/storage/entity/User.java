package com.khamovniki.vienna.storage.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.khamovniki.vienna.storage.entity.converter.StringSetConverter;

import lombok.Data;

@Entity
@Data
public class User {

    @Id
    private long userId;
    @Lob
    @Convert(converter = StringSetConverter.class)
    private Set<String> tags = new HashSet<>();
}
