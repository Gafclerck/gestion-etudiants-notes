package com.ism.factory;

import com.ism.repository.EtudiantRepository;
import com.ism.repository.NoteRepository;
import com.ism.service.EtudiantService;
import com.ism.service.MoyenneService;
import com.ism.service.MoyenneServiceImpl;
import com.ism.service.NoteService;
import com.ism.service.EtudiantServiceImpl;
import com.ism.service.NoteServiceImpl;

public class ServiceFactory {
    public static EtudiantService createEtudiantService(EtudiantRepository etudiantRepository,
            NoteRepository noteRepository, MoyenneService moyenneService) {
        return new EtudiantServiceImpl(etudiantRepository, noteRepository, moyenneService);
    }

    public static NoteService createNoteService(NoteRepository noteRepository) {
        return new NoteServiceImpl(noteRepository);
    }

    public static MoyenneService createMoyenneService() {
        return new MoyenneServiceImpl();
    }
}
