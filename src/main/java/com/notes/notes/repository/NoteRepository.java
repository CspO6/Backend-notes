package com.notes.notes.repository;

import com.notes.notes.model.Note;
import com.notes.notes.model.Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {


    @Query("SELECT n FROM Note n WHERE n.archived = false")
    List<Note> findActiveNotes();

 
    @Query("SELECT n FROM Note n WHERE n.archived = true")
    List<Note> findArchivedNotes();

    List<Note> findByTagsContaining(Tag tag);
   
    @Transactional
    @Modifying
    @Query("UPDATE Note n SET n.archived = :archived WHERE n.id = :id")
    void updateArchivedStatus(@Param("id") Long id, @Param("archived") boolean archived);

    List<Note> findByTagsName(String tagName);

    
}