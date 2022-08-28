package fr.pizzahut.pizzahutrhms.services;

import fr.pizzahut.pizzahutrhms.mappers.PieceJointeDaoToDtoMapper;
import fr.pizzahut.pizzahutrhms.models.dao.EmployeeDao;
import fr.pizzahut.pizzahutrhms.models.dao.PieceJointeDao;
import fr.pizzahut.pizzahutrhms.models.dto.PieceJointe;
import fr.pizzahut.pizzahutrhms.repositories.PieceJointeRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class PieceJointeService {

    private final PieceJointeRespository pieceJointeRespository;
    private final PieceJointeDaoToDtoMapper pieceJointeDaoToDtoMapper;

    @Autowired
    public PieceJointeService(PieceJointeRespository pieceJointeRespository,
                              PieceJointeDaoToDtoMapper pieceJointeDaoToDtoMapper) {
        this.pieceJointeRespository = pieceJointeRespository;
        this.pieceJointeDaoToDtoMapper = pieceJointeDaoToDtoMapper;
    }

    public void savePjs(List<MultipartFile> piecesJointes, EmployeeDao employee) {
        if (!CollectionUtils.isEmpty(piecesJointes)) {
            piecesJointes.forEach(pieceJointe -> {
                try {
                    log.info("Sauvegarde de la pièce jointe : {}", pieceJointe.getName());
                    PieceJointeDao pieceJointeDao = new PieceJointeDao();
                    pieceJointeDao.setNom(StringUtils.cleanPath(Objects.requireNonNull(pieceJointe.getOriginalFilename())));
                    pieceJointeDao.setType(pieceJointe.getContentType());
                    pieceJointeDao.setContenu(pieceJointe.getBytes());
                    pieceJointeDao.setTaille(pieceJointe.getSize());
                    pieceJointeDao.setEmployee(employee);
                    // sauvegarder la pj dans la bd
                    this.pieceJointeRespository.saveAndFlush(pieceJointeDao);
                } catch (IOException e) {
                    log.error("Erreur lors de la sauvegarde de la pièce jointe : {}", pieceJointe.getName());
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public void editPjs(List<MultipartFile> piecesJointes, EmployeeDao employee) {
        // Supprimer toutes les pjs et les recréer
        this.pieceJointeRespository.deleteAllByEmployee_Id(employee.getId());
        // Sauvegarder à nouveau les nouvelles pièces-jointes
        this.savePjs(piecesJointes, employee);
    }

    public List<PieceJointe> getEmployeePjs(Long id) {
        return this.pieceJointeDaoToDtoMapper.mapList(this.pieceJointeRespository.findAllByEmployee_Id(id));
    }

    public PieceJointe downloadPj(Long id) {
        Optional<PieceJointeDao> pj = this.pieceJointeRespository.findById(id);
        if (pj.isPresent()) {
            return this.pieceJointeDaoToDtoMapper.daoToDto(pj.get());
        } else {
            String errorMsg = "La pièce jointe avec l'id " + id + " est introuvable.";
            log.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
    }
}
