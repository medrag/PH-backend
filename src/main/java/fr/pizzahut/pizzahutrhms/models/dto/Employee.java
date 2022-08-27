package fr.pizzahut.pizzahutrhms.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String dateNaissance;
    private String telephone;
    private String numSecuriteSociale;
    private String nationalite;
    private String dateEmbauche;
    private String poste;
    private double tauxHoraire;
    private double nbrHeures;
    private boolean mutuelle;
    private boolean tempsPartiel;
    private List<String> jrsSemaine;
    private String commentaire;
    private String dateSortie;
    private String causeSortie;
    private String dernierJrTravaille;
    private boolean actif;
    private Company company;
}
