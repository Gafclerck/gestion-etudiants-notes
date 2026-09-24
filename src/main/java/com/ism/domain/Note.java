package com.ism.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString 
@Builder 
public class Note {
    private int id;
    private String matiere;
    private double valeur;
    private Etudiant etudiant;
    private LocalDateTime createAt;
    private LocalDateTime updateAt; 
}
