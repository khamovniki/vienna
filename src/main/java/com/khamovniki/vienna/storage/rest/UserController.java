package com.khamovniki.vienna.storage.rest;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.khamovniki.vienna.storage.dto.TagChangeRequestDto;
import com.khamovniki.vienna.storage.service.UserService;

import lombok.RequiredArgsConstructor;

import static com.khamovniki.vienna.ViennaApplication.APPLICATION_NAME;

@RestController
@RequestMapping(APPLICATION_NAME + "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@PathVariable long userId) {
        userService.createUser(userId);
    }

    @PostMapping("/{userId}/addTag")
    public void addTag(@PathVariable long userId,
            String tag) {
        TagChangeRequestDto request = TagChangeRequestDto.builder()
                .userId(userId)
                .tag(tag)
                .build();
        userService.addUserTag(request);
    }

    @GetMapping("/{userId}/listAbsentTags")
    public Set<String> listAbsentTags(@PathVariable long userId) {
        return userService.listAbsentTags(userId);
    }

    @PostMapping("/{userId}/removeTag")
    public void removeTag(@PathVariable long userId,
            String tag) {
        TagChangeRequestDto request = TagChangeRequestDto.builder()
                .userId(userId)
                .tag(tag)
                .build();
        userService.removeUserTag(request);
    }


    @GetMapping("/{userId}/listTags")
    public Set<String> listTags(@PathVariable long userId) {
        return userService.listTags(userId);
    }

}
