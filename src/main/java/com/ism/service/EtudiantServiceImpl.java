package com.ism.service;

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
        Etudiant newEtudiant = new Etudiant(0, nouveau.nomComplet, nouveau.matricule, nouveau.classe);
        return repo.insert(newEtudiant);
    }

    @Override
    public List<Etudiant> findAll() {
        return repo.selectAll();
    }
    
}
