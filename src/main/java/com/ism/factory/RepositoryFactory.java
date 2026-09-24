package com.ism.factory;

import com.ism.repository.EtudiantRepository;
import com.ism.repository.memory.DatabaseMemory;
import com.ism.repository.memory.EtudiantRepositoryMemory;

public final class RepositoryFactory {
    public static EtudiantRepository createEtudiantRepository() {
        return new EtudiantRepositoryMemory(DatabaseMemory.getInstance());
    }
}
