package com.notes.notes.service;

import com.notes.notes.dto.NoteRequest;
import com.notes.notes.model.Note;
import com.notes.notes.model.Tag;
import com.notes.notes.repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private TagService tagService; // Inyectar el servicio de tags

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note createNote(NoteRequest noteRequest) {
        Note note = new Note();
        note.setTitle(noteRequest.getTitle());
        note.setContent(noteRequest.getContent());

        // Convertir los nombres de los tags en objetos Tag
        Set<Tag> tags = noteRequest.getTags().stream()
                .map(tagService::getOrCreateTag) // Obtener o crear cada tag
                .collect(Collectors.toSet());
        note.setTags(tags);

        return noteRepository.save(note);
    }

    public Note updateNote(Long id, NoteRequest updatedNote) {
        // Buscar la nota existente
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
    
        // Actualizar campos básicos
        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());
    
        // Actualizar tags
        Set<Tag> tags = updatedNote.getTags().stream()
                .map(tagService::getOrCreateTag) // Obtener o crear cada tag
                .collect(Collectors.toSet());
        note.setTags(tags);
    
        // Actualizar el estado archived
        note.setArchived(updatedNote.isArchived()); // Asegúrate de que NoteRequest tenga este campo
    
        // Guardar la nota actualizada
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> getActiveNotes() {
        return noteRepository.findActiveNotes();
    }

    public List<Note> getArchivedNotes() {
        return noteRepository.findArchivedNotes();
    }

  
    public Note updateArchivedStatus(Long id, boolean archived) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
    
        note.setArchived(archived);
        return noteRepository.save(note);
    }

    // Método para buscar notas por tags
    public List<Note> getNotesByTag(String tagName) {
        return noteRepository.findByTagsName(tagName);
    }

   

}