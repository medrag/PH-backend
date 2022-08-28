package fr.pizzahut.pizzahutrhms.services;

import fr.pizzahut.pizzahutrhms.mappers.CompanyDaoToDtoMapper;
import fr.pizzahut.pizzahutrhms.mappers.CompanyDtoToDaoMapper;
import fr.pizzahut.pizzahutrhms.models.dto.Company;
import fr.pizzahut.pizzahutrhms.repositories.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyDaoToDtoMapper companyDaoToDtoMapper;
    private final CompanyDtoToDaoMapper companyDtoToDaoMapper;

    @Autowired
    public CompanyService(CompanyRepository companyRepository,
                          CompanyDaoToDtoMapper companyDaoToDtoMapper,
                          CompanyDtoToDaoMapper companyDtoToDaoMapper) {
        this.companyRepository = companyRepository;
        this.companyDaoToDtoMapper = companyDaoToDtoMapper;
        this.companyDtoToDaoMapper = companyDtoToDaoMapper;
    }

    public List<Company> getAllCompanies() {
        log.info("Récupèration de toutes les entreprises");
        return this.companyDaoToDtoMapper.mapList(this.companyRepository.findAll());
    }

    public void saveNewCompany(Company company) {
        log.info("Sauvegarde d'une nouvelle entreprise : {}", company);
        this.companyRepository.saveAndFlush(this.companyDtoToDaoMapper.dtoToDao(company));
    }

    public void editCompany(Company company) {
        log.info("Modification de l'entreprise : {}", company);
        boolean exists = this.companyRepository.existsById(company.getId());
        if (exists) {
            this.companyRepository.saveAndFlush(this.companyDtoToDaoMapper.dtoToDao(company));
        } else {
            throw new RuntimeException("L'entreprise à modifier n'existe pas");
        }
    }

    public void deleteCompany(Company company) {
        log.info("Suppression de l'entreprise : {}", company);
        this.companyRepository.delete(this.companyDtoToDaoMapper.dtoToDao(company));
    }
}
