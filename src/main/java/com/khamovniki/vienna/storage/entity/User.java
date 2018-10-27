package com.khamovniki.vienna.storage.entity;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class User {

    @Id
    private Long userId;

    //for the sake of hackathon
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_tag",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_name")
    )
    @EqualsAndHashCode.Exclude
    private Set<Tag> tags;

    public Set<String> listTagsNames() {
        return this.tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null) return false;
        if (!(obj instanceof User))
            return false;
        if (obj == this)
            return true;
        return this.userId.equals(((User) obj).userId);
    }

    @Override
    public int hashCode() {
        return this.userId.hashCode();
    }

}
