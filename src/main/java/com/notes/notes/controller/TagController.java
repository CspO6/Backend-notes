package com.notes.notes.controller;

import com.notes.notes.dto.TagDTO;
import com.notes.notes.model.Tag;
import com.notes.notes.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "https://frontend-notes-ky1y03cnf-cspo6s-projects.vercel.app")
public class TagController {

    @Autowired
    private TagService tagService;

 
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

  
    @PostMapping
    public Tag createTag(@RequestBody TagDTO tagDTO) {
        return tagService.getOrCreateTag(tagDTO.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}