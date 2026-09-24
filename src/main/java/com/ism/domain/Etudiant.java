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
public class Etudiant {
    private int id;
    private String nomComplet;
    private String matricule;
    private String classe;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;    
}
