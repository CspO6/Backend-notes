package com.notes.notes.service;

import com.notes.notes.model.Tag;
import com.notes.notes.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;


    public Tag getOrCreateTag(String tagName) {
        return tagRepository.findByName(tagName)
                .orElseGet(() -> {
                    Tag newTag = new Tag();
                    newTag.setName(tagName);
                    return tagRepository.save(newTag);
                });
    }


    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public void deleteTag(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            // Desasociar el tag de todas las notas antes de eliminarlo
            Tag existingTag = tag.get();
            existingTag.getNotes().forEach(note -> note.getTags().remove(existingTag));
            tagRepository.delete(existingTag);
        } else {
            throw new RuntimeException("Tag no encontrado con ID: " + id);
        }
    }
}