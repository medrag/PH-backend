package fr.pizzahut.pizzahutrhms.repositories;

import fr.pizzahut.pizzahutrhms.models.dao.CompanyDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDao, Long> {
}
