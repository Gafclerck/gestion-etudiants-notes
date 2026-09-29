package com.ism.service;

import java.util.List;

import com.ism.domain.Etudiant;

public interface NoteService {
    boolean addNote(Note nouvelle, String matricule);
    List<Note> findAllNotesByEtudiant(String matricule);
}
