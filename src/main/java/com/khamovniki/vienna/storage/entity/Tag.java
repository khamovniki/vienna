package com.khamovniki.vienna.storage.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Tag {

    @Id
    private String name;
}
