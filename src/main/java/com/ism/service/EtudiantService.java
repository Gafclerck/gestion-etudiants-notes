package com.ism.service;

import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.dto.EtudiantCreateDto;

public interface EtudiantService {
    boolean addEtudiant(EtudiantCreateDto nouveau);
    List<Etudiant> findAll();
}
