package fr.pizzahut.pizzahutrhms.models.dao;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "company")
public class CompanyDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String numSiret;
    private String adresse;
    @OneToMany(mappedBy = "company")
    private List<EmployeeDao> employees = new ArrayList<>(0);
}
