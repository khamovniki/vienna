package com.khamovniki.vienna.storage.rest;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khamovniki.vienna.storage.service.TagService;

import lombok.RequiredArgsConstructor;

import static com.khamovniki.vienna.ViennaApplication.APPLICATION_NAME;

@RestController
@RequestMapping(APPLICATION_NAME + "/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/list")
    public Set<String> listTags() {
        return tagService.listTags();
    }

    @PostMapping("/create/{tagName}")
    public void createTag() {
    }
}
