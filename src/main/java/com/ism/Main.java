package com.ism;

import java.util.Scanner;

import com.ism.domain.Etudiant;
import com.ism.dto.EtudiantCreateDto;
import com.ism.dto.EtudiantListeDto;
import com.ism.dto.EtudiantSimpleDto;
import com.ism.dto.NoteCreateDto;
import com.ism.dto.NoteListeDto;
import com.ism.exception.EtudiantNotFoundException;
import com.ism.exception.MatriculeAlreadyExistsException;
import com.ism.exception.ValidationException;
import com.ism.factory.RepositoryFactory;
import com.ism.factory.ServiceFactory;
import com.ism.service.EtudiantService;
import com.ism.service.NoteService;

public class Main {
    public static void main(String[] args) {
        EtudiantService es = ServiceFactory.createEtudiantService(
                RepositoryFactory.createEtudiantRepository(),
                RepositoryFactory.createNoteRepository(),
                ServiceFactory.createMoyenneService());
        NoteService ns = ServiceFactory.createNoteService(RepositoryFactory.createNoteRepository());

        Scanner scanner = new Scanner(System.in);
        String choix;

        do {
            System.out.println("1 - Créer étudiant");
            System.out.println("2 - Lister étudiants");
            System.out.println("3 - Ajouter note");
            System.out.println("4 - Lister les étudiants avec leurs notes et leur moyenne");
            System.out.println("0 - Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextLine().trim();

            switch (choix) {
                case "1":
                    try {
                        System.out.print("Nom complet : ");
                        String nom = scanner.nextLine().trim();
                        System.out.print("Matricule : ");
                        String matricule = scanner.nextLine().trim();
                        System.out.print("Classe : ");
                        String classe = scanner.nextLine().trim();

                        EtudiantCreateDto nouveau = new EtudiantCreateDto(nom, matricule, classe);
                        es.addEtudiant(nouveau);
                        System.out.println("Succès");
                    } catch (ValidationException | MatriculeAlreadyExistsException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                    break;

                case "2":
                    var etudiants = es.findAll();
                    for (EtudiantSimpleDto etudiant : etudiants) {
                        System.out.println("Matricule : " + etudiant.matricule() + " | Nom : " + etudiant.nomComplet() + " | Classe : " + etudiant.classe());
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Matricule : ");
                        String mat = scanner.nextLine().trim();
                        var etudiant = es.findByMatricule(mat);
                        if (etudiant.isEmpty()) {
                            throw new EtudiantNotFoundException("Ce matricule ne correspond à aucun étudiant : " + mat);
                        }

                        System.out.print("Matière : ");
                        String matiere = scanner.nextLine().trim();
                        System.out.print("Valeur : ");
                        double valeur = Double.parseDouble(scanner.nextLine().trim());

                        NoteCreateDto nouvelle = new NoteCreateDto(valeur, matiere, etudiant.get());
                        ns.addNote(nouvelle);
                        System.out.println("Succès");
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur : Valeur numérique invalide");
                    } catch (ValidationException | EtudiantNotFoundException e) {
                        System.out.println("Erreur : " + e.getMessage());
                    }
                    break;

                case "4":
                    var etudiantsDto = es.findAllWithNotes();
                    for (EtudiantListeDto etudiantDto : etudiantsDto) {
                        System.out.println("Nom : " + etudiantDto.nomComplet() + " | Matricule : " + etudiantDto.matricule() + " | Moyenne : " + etudiantDto.moyenne());
                        for (NoteListeDto noteDto : etudiantDto.notes()) {
                            System.out.println("  Matière : " + noteDto.matiere() + " | Note : " + noteDto.valeur());
                        }
                    }
                    break;

                case "0":
                    break;

                default:
                    System.out.println("Choix invalide");
                    break;
            }
        } while (!choix.equals("0"));

        scanner.close();
    }
}
