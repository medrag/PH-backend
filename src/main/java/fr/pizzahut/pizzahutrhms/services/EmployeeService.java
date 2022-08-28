package fr.pizzahut.pizzahutrhms.services;

import fr.pizzahut.pizzahutrhms.mappers.EmployeeDaoToDtoMapper;
import fr.pizzahut.pizzahutrhms.mappers.EmployeeDtoToDaoMapper;
import fr.pizzahut.pizzahutrhms.models.dao.EmployeeDao;
import fr.pizzahut.pizzahutrhms.models.dto.Employee;
import fr.pizzahut.pizzahutrhms.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDtoToDaoMapper employeeDtoToDaoMapper;
    private final EmployeeDaoToDtoMapper employeeDaoToDtoMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeDtoToDaoMapper employeeDtoToDaoMapper,
                           EmployeeDaoToDtoMapper employeeDaoToDtoMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeDtoToDaoMapper = employeeDtoToDaoMapper;
        this.employeeDaoToDtoMapper = employeeDaoToDtoMapper;
    }

    public List<Employee> getAllEmployees() {
        log.info("Récupération de tous les employés");
        return this.employeeDaoToDtoMapper.mapList(this.employeeRepository.findAll());
    }

    public EmployeeDao saveNewEmployee(Employee employee) {
        log.info("Ajout de l'employé(e) : {}", employee);
        return this.employeeRepository.saveAndFlush(this.employeeDtoToDaoMapper.dtoToDao(employee));
    }

    public EmployeeDao editEmployee(Employee employee) {
        log.info("Modification de l'employé(e) : {}", employee);
        boolean employeeExists = this.employeeRepository.existsById(employee.getId());
        if (employeeExists) {
            return this.employeeRepository.saveAndFlush(this.employeeDtoToDaoMapper.dtoToDao(employee));
        } else {
            throw new RuntimeException("L'employé(e) à modifier n'existe pas");
        }
    }

    public void deleteEmployee(Employee employee) {
        log.info("Suppression de l'employé : {}", employee);
        this.employeeRepository.delete(this.employeeDtoToDaoMapper.dtoToDao(employee));
    }

    public EmployeeDao deactivateEmployee(Employee employee) {
        log.info("Désactivation de l'employé {}", employee);
        Optional<EmployeeDao> savedEmp = this.employeeRepository.findById(employee.getId());
        EmployeeDao employeeToDeactivate = new EmployeeDao();
        if (savedEmp.isPresent()) {
            employeeToDeactivate = savedEmp.get();
            employeeToDeactivate.setDateSortie(employee.getDateSortie());
            employeeToDeactivate.setCauseSortie(employee.getCauseSortie());
            employeeToDeactivate.setDernierJrTravaille(employee.getDernierJrTravaille());
            employeeToDeactivate.setActif(employee.isActif());
        } else {
            log.error("Profil employé avec l'id {} est inexistant", employee.getId());
        }
        return this.employeeRepository.saveAndFlush(employeeToDeactivate);
    }
}
