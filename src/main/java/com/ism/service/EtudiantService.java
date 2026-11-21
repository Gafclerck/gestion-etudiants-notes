package com.ism.service;

import java.util.List;
import java.util.Optional;

import com.ism.domain.Etudiant;
import com.ism.dto.EtudiantCreateDto;
import com.ism.dto.EtudiantListeDto;
import com.ism.dto.EtudiantSimpleDto;

public interface EtudiantService {
    boolean addEtudiant(EtudiantCreateDto nouveau);
    List<EtudiantSimpleDto> findAll();
    List<EtudiantListeDto> findAllWithNotes();
    Optional<Etudiant> findByMatricule(String matricule);
}
