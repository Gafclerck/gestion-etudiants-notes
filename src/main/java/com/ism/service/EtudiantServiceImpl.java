package com.ism.service;

import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.repository.EtudiantRepository;

public class EtudiantServiceImpl implements EtudiantService{
    private EtudiantRepository repo;

    public EtudiantServiceImpl(EtudiantRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean addEtudiant(EtudiantCreateDto nouveau) {
        // mapping from DTO to domain object
        Etudiant etudiant = new Etudiant.builder()
                .nomComplet(nouveau.getNomComplet())
                .matricule(nouveau.getMatricule())
                .classe(nouveau.getClasse())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return repo.insert(etudiant);
    }

    @Override
    public List<Etudiant> findAll() {
        return repo.selectAll();
    }
    
}
