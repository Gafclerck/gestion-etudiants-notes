package com.ism.service;

import java.util.List;

import com.ism.domain.Etudiant;

public interface NoteService {
    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    boolean addNote(Note nouvelle, String matricule){
        return noteRepository.add(nouvelle, matricule);
    }

    List<Note> findAllNotesByEtudiant(String matricule){
        return noteRepository.selectByEtudiant(matricule);
    }
    
    Double calculateAverage(String matricule) {
        List<Note> notes = noteRepository.selectByEtudiant(matricule);
        if (notes == null || notes.isEmpty()) {
            return null;
        }
        double sum = 0;
        for (Note note : notes) {
            sum += note.getNote();
        }
        return sum / notes.size();
    }
}
