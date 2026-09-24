package com.ism.repository.memory;

import java.util.List;

import com.ism.domain.Etudiant;
import com.ism.repository.EtudiantRepository;

public class EtudiantRepositoryMemory implements EtudiantRepository {
    private DatabaseMemory dbm;

    public EtudiantRepositoryMemory(DatabaseMemory dbm) {
        this.dbm = dbm;
    }

    @Override
    public boolean insert(Etudiant nouveau) {
        dbm.etudiants.add(nouveau);
        return true;
    }

    @Override
    public List<Etudiant> selectAll() {
        return dbm.etudiants;
    }
    
}
