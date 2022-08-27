package fr.pizzahut.pizzahutrhms.models.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class EmployeeDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String jrsSemaine;
    private String commentaire;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PieceJointeDao> piecesJointes = new ArrayList<>(0);
    private String dateSortie;
    private String causeSortie;
    private String dernierJrTravaille;
    @Column(name = "actif", columnDefinition = "boolean default true")
    private boolean actif = true;
    @ManyToOne
    @JoinColumn(name = "company_fk_id")
    private CompanyDao company;
}
