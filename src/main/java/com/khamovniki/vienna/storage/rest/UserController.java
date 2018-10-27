package com.khamovniki.vienna.storage.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public void createUser(@PathVariable String userId) {
        userService.createUser(userId);
    }

    @PostMapping("/{userId}/addTag")
    public void addTag(@PathVariable String userId,
            String tag) {
        TagChangeRequestDto request = TagChangeRequestDto.builder()
                .userId(userId)
                .tag(tag)
                .build();
        userService.addUserTag(request);
    }

    @PostMapping("/{userId}/removeTag")
    public void removeTag(@PathVariable String userId,
            String tag) {
        TagChangeRequestDto request = TagChangeRequestDto.builder()
                .userId(userId)
                .tag(tag)
                .build();
        userService.removeUserTag(request);
    }
}
