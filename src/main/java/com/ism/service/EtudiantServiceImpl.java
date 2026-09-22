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
    public boolean addEtudiant(Etudiant nouveau) {
        return repo.insert(nouveau);
    }

    @Override
    public List<Etudiant> findAll() {
        return repo.selectAll();
    }
    
}
