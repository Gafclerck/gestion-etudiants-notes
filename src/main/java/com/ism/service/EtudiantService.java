package com.ism.service;

import java.util.List;

import com.ism.domain.Etudiant;

public interface EtudiantService {
    boolean addEtudiant(Etudiant nouveau);
    List<Etudiant> findAll();
}
