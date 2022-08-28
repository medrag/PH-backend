package fr.pizzahut.pizzahutrhms.repositories;

import fr.pizzahut.pizzahutrhms.models.dao.PieceJointeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PieceJointeRespository extends JpaRepository<PieceJointeDao, Long> {
    void deleteAllByEmployee_Id(Long id);
    List<PieceJointeDao> findAllByEmployee_Id(Long id);
}
