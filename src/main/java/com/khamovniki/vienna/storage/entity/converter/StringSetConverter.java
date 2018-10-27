package com.khamovniki.vienna.storage.entity.converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.google.common.base.Joiner;

@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        return Joiner.on(",").join(attribute);
    }

    @Override
    public Set<String> convertToEntityAttribute(String joined) {
        return new HashSet<>(Arrays.asList(joined.split(",")));
    }
}
