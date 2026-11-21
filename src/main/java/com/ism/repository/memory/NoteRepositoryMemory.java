package com.ism.repository.memory;

import java.util.ArrayList;
import java.util.List;

import com.ism.domain.Note;
import com.ism.repository.NoteRepository;

public class NoteRepositoryMemory implements NoteRepository {
    private DatabaseMemory dbm;

    public NoteRepositoryMemory(DatabaseMemory dbm) {
        this.dbm = dbm;
    }

    @Override
    public boolean insert(Note nouveau) {
        dbm.notes.add(nouveau);
        return true;
    }

    @Override
    public List<Note> selectByEtudiant(String matricule) {
        var notes = dbm.notes;
        List<Note> notesByEtudiant = new ArrayList<>();
        for (Note note : notes) {
            if (note.getEtudiant().getMatricule().equals(matricule)) {
                notesByEtudiant.add(note);
            }
        }
        return notesByEtudiant;
    }
    
}
