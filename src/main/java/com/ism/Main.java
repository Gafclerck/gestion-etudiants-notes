package com.ism;

import com.ism.domain.Etudiant;
import com.ism.factory.RepositoryFactory;
import com.ism.service.EtudiantService;
import com.ism.service.EtudiantServiceImpl;
import com.ism.dto.EtudiantCreateDto;
import com.ism.dto.NoteCreateDto;
import com.ism.dto.EtudiantListeDto;
import com.ism.dto.NoteListeDto;
import com.ism.service.MoyenneService;
import com.ism.service.MoyenneServiceImpl;
import com.ism.service.NoteService;
import com.ism.service.NoteServiceImpl;

public class Main {
    public static void main(String[] args) {
        // FACTORY
        MoyenneService ms = new MoyenneServiceImpl();
        EtudiantService es = new EtudiantServiceImpl(RepositoryFactory.createEtudiantRepository(), RepositoryFactory.createNoteRepository(), ms);
        NoteService ns = new NoteServiceImpl(RepositoryFactory.createNoteRepository());

        String choix;
        do {
            System.out.println("1 - Créer étudiant");
            System.out.println("2 - Lister étudiants");
            System.out.println("3 - Ajouter note");
            System.out.println("4 - Lister les étudiants avec leurs notes et leur moyenne");
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

                case "3":
                    matricule = System.console().readLine("Matricule : ");
                    var etudiant = es.findByMatricule(matricule);
                    if (etudiant.isEmpty()) {
                        System.out.println("Ce matricule ne correspond à aucun étudiant");
                    } else {
                        String matiere = System.console().readLine("Matricule : ");;
                        double valeur = Double.parseDouble(System.console().readLine("Valeur : "));
                        NoteCreateDto nouvelle = new NoteCreateDto(valeur, matiere, etudiant.get());
                        success = ns.addNote(nouvelle);
                    }
                    break;

                case "4":
                    var etudiantsDto = es.findAllWithNotes();
                    for (EtudiantListeDto etudiantDto : etudiantsDto) {
                        System.out.println("Nom : " + etudiantDto.nomComplet());
                        System.out.println("Matricule : " + etudiantDto.matricule());
                        System.out.println("Moyenne : " + etudiantDto.moyenne());
                        for (NoteListeDto noteDto : etudiantDto.notes()) {
                            System.out.println("Matière : " + noteDto.matiere());
                            System.out.println("Note : " + noteDto.valeur());
                        }
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