package fr.pizzahut.pizzahutrhms.controllers;

import fr.pizzahut.pizzahutrhms.models.dto.Company;
import fr.pizzahut.pizzahutrhms.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(this.companyService.getAllCompanies());
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveNewCompany(@RequestBody Company company) {
        this.companyService.saveNewCompany(company);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editCompany(@RequestBody Company company) {
        this.companyService.editCompany(company);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCompany(@RequestBody Company company) {
        this.companyService.deleteCompany(company);
        return ResponseEntity.ok().build();
    }
}
