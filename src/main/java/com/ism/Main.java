package com.ism;

import com.ism.domain.Etudiant;
import com.ism.factory.RepositoryFactory;
import com.ism.service.EtudiantService;
import com.ism.service.EtudiantServiceImpl;
import com.ism.dto.EtudiantCreateDto;

public class Main {
    public static void main(String[] args) {
        // FACTORY
        EtudiantService es = new EtudiantServiceImpl(RepositoryFactory.createEtudiantRepository());

        String choix;
        do {
            System.out.println("1 - Créer étudiant");
            System.out.println("2 - Lister étudiants");
            System.err.println("0 - Quitter");
            choix = System.console().readLine("Votre choix : ");

            switch (choix) {
                case "1":
                    String nom = System.console().readLine("Nom complet : ");
                    String matricule = System.console().readLine("Matricule : ");
                    String classe = System.console().readLine("Classe : ");
                    EtudiantCreateDto nouveau = new EtudiantCreateDto(nom, matricule, classe);
                    boolean success = es.addEtudiant(nouveau);
                    if (success) {
                        System.out.println("Succès");
                    } else {
                        System.out.println("Erreur");
                    }
                    break;

                case "2":
                    var etudiants = es.findAll();
                    for (Etudiant etudiant : etudiants) {
                        System.out.println(etudiant);
                    }
                    break;

                case "0":
                    
                    break;
            
                default:
                    break;
            }
        } while (!choix.equals("0"));
    }
}