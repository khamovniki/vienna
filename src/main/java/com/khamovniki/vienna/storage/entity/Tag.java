package com.khamovniki.vienna.storage.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Tag {

    @Id
    private String name;

    //for the sake of hackathon
    @ManyToMany(mappedBy = "tags")
    @EqualsAndHashCode.Exclude
    private Set<User> users;
}
