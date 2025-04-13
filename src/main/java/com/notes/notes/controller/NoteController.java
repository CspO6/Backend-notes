package com.notes.notes.controller;

import com.notes.notes.dto.ArchivedStatusRequest;
import com.notes.notes.dto.NoteRequest;
import com.notes.notes.model.Note;
import com.notes.notes.service.NoteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "https://frontend-notes-1vkkc57c5-cspo6s-projects.vercel.app")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody @Valid NoteRequest noteRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(noteRequest));
}
    
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody NoteRequest noteRequest) {
        return ResponseEntity.ok(noteService.updateNote(id, noteRequest));
    }
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }

    @GetMapping("/active")
    public List<Note> getActiveNotes() {
        return noteService.getActiveNotes();
    }

    @GetMapping("/archived")
    public List<Note> getArchivedNotes() {
        return noteService.getArchivedNotes();
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Note> updateArchivedStatus(
        @PathVariable Long id,
        @RequestBody ArchivedStatusRequest archivedStatusRequest) {
    Note updatedNote = noteService.updateArchivedStatus(id, archivedStatusRequest.isArchived());
    return ResponseEntity.ok(updatedNote);
}

    @GetMapping("/by-tag")
    public List<Note> getNotesByTag(@RequestParam String tagName) {
        return noteService.getNotesByTag(tagName);
    }
}