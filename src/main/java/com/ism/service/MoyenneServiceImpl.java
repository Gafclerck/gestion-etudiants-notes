package com.ism.service;

import java.util.List;

import com.ism.domain.Note;

public class MoyenneServiceImpl implements MoyenneService {

    @Override
    public double moyenne(List<Note> notes) {
        if (notes == null || notes.isEmpty()) {
            return 0.0;
        }
        return notes.stream()
                .mapToDouble(Note::getValeur)
                .average()
                .orElse(0.0);
    }
    
}
