package com.ism.repository.memory;

import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.repository.EtudiantRepository;

public class NoteRepositoryMemory implements NoteRepository {
    public DatabaseMemory dbm;

    public NoteRepositoryMemory(DatabaseMemory dbm) {
        this.dbm = dbm;
    }

    @Override
    public boolean insert(Note newNote, String matricule) {
        dbm.notes.computeIfAbsent(matricule, k -> new ArrayList<>()).add(newNote);
        return true;
    }

    @Override
    public List<Note> selectByEtudiant(String matricule) {
        return dbm.notes.get(matricule);
    }
}