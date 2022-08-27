package fr.pizzahut.pizzahutrhms.models.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "piece_jointe")
@Getter
@Setter
public class PieceJointeDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Long taille;
    private String type;
    @Lob
    private byte[] contenu;
    @ManyToOne
    @JoinColumn(name = "emp_fk_id", nullable = false)
    private EmployeeDao employee;
}
