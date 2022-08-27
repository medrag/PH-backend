package fr.pizzahut.pizzahutrhms.repositories;

import fr.pizzahut.pizzahutrhms.models.dao.EmployeeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDao, Long> {
}
