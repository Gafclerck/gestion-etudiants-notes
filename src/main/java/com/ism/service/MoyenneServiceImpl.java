package com.ism.service;

import java.util.List;

import com.ism.domain.Note;

public class MoyenneServiceImpl implements MoyenneService {

    @Override
    public double moyenne(List<Note> notes) {
        double somme = 0;
        for (Note note : notes) {
            somme += note.getValeur();
        }
        double moy = somme / notes.size();
        return moy;
    }
    
}
