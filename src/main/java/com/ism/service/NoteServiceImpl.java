package com.ism.service;

import com.ism.dto.NoteCreateDto;
import com.ism.exception.ValidationException;
import com.ism.repository.NoteRepository;

import java.time.LocalDateTime;

import com.ism.domain.Note;

public class NoteServiceImpl implements NoteService {
    private NoteRepository repo;

    public NoteServiceImpl(NoteRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean addNote(NoteCreateDto nouveau) {
        if (nouveau == null) {
            throw new ValidationException("Les informations de la note sont obligatoires");
        }
        if (nouveau.matiere() == null || nouveau.matiere().isBlank()) {
            throw new ValidationException("La matière est obligatoire");
        }
        if (nouveau.valeur() < 0 || nouveau.valeur() > 20) {
            throw new ValidationException("La note doit être comprise entre 0 et 20");
        }
        if (nouveau.etudiant() == null) {
            throw new ValidationException("L'étudiant associé est obligatoire");
        }

        Note newNote = Note.builder()
                        .matiere(nouveau.matiere())
                        .valeur(nouveau.valeur())
                        .etudiant(nouveau.etudiant())
                        .build();
        newNote.setCreateAt(LocalDateTime.now());
        return repo.insert(newNote);
    }
    
}
