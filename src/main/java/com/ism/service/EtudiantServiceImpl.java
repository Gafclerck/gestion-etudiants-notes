package com.ism.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ism.domain.Etudiant;
import com.ism.domain.Note;
import com.ism.repository.EtudiantRepository;
import com.ism.repository.NoteRepository;
import com.ism.dto.EtudiantCreateDto;
import com.ism.dto.EtudiantListeDto;
import com.ism.dto.EtudiantSimpleDto;
import com.ism.dto.NoteListeDto;

public class EtudiantServiceImpl implements EtudiantService{
    private EtudiantRepository repo;
    private NoteRepository repoNote;
    private MoyenneService moyenneService;

    public EtudiantServiceImpl(EtudiantRepository repo, NoteRepository repoNote, MoyenneService moyenneService) {
        this.repo = repo;
        this.repoNote = repoNote;
        this.moyenneService = moyenneService;
    }

    @Override
    public boolean addEtudiant(EtudiantCreateDto nouveau) { 
        // mapping dto to entity
        Etudiant newEtudiant = Etudiant.builder() 
                                        .nomComplet(nouveau.nomComplet)
                                        .matricule(nouveau.matricule)
                                        .classe(nouveau.classe)
                                        // .createAt(LocalDateTime.now())
                                        .build();
        newEtudiant.setCreateAt(LocalDateTime.now());
        return repo.insert(newEtudiant);
    }

    @Override
    public List<EtudiantSimpleDto> findAll() {
        return repo.selectAll().stream()
                .map(e -> new EtudiantSimpleDto(e.getMatricule(), e.getNomComplet(), e.getClasse()))
                .toList();
    }

    @Override
    public List<EtudiantListeDto> findAllWithNotes() {
        List<EtudiantListeDto> liste = new ArrayList<>();
        var etudiants = repo.selectAll();
        for (Etudiant etudiant : etudiants) {
            var notes = repoNote.selectByEtudiant(etudiant.getMatricule());
            List<NoteListeDto> notesDto = new ArrayList<>();
            for (Note note : notes) {
                NoteListeDto noteDto = new NoteListeDto(note.getValeur(), note.getMatiere());
                notesDto.add(noteDto);
            }
            EtudiantListeDto et = new EtudiantListeDto(etudiant.getMatricule(), etudiant.getNomComplet(), notesDto, moyenneService.moyenne(notes));
            liste.add(et);
        }
        return liste;
    }

    @Override
    public Optional<Etudiant> findByMatricule(String matricule) {
        return repo.selectByMatricule(matricule);
    }
    
}
