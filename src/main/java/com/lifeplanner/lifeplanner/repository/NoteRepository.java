package com.lifeplanner.lifeplanner.repository;

import com.lifeplanner.lifeplanner.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}