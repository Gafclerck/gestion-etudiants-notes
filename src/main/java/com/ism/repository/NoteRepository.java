package com.ism.repository;

import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.domain.Note;

public interface NoteRepository {
    boolean add(Note nouvelle, String matricule);
    List<Note> selectByEtudiant(String matricule);
}