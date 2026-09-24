package com.ism.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.repository.EtudiantRepository;
import com.ism.dto.EtudiantCreateDto;

public class EtudiantServiceImpl implements EtudiantService{
    private EtudiantRepository repo;

    public EtudiantServiceImpl(EtudiantRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean addEtudiant(EtudiantCreateDto nouveau) { 
        // mapping dto to entity
        Etudiant newEtudiant = Etudiant.builder() 
                                        .nomComplet(nouveau.nomComplet)
                                        .matricule(nouveau.matricule)
                                        .classe(nouveau.classe)
                                        .createAt(LocalDateTime.now())
                                        .build();
        return repo.insert(newEtudiant);
    }

    @Override
    public List<Etudiant> findAll() {
        return repo.selectAll();
    }
    
}
