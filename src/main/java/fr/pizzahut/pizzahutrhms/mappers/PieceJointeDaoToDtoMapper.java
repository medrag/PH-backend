package fr.pizzahut.pizzahutrhms.mappers;

import fr.pizzahut.pizzahutrhms.models.dao.PieceJointeDao;
import fr.pizzahut.pizzahutrhms.models.dto.PieceJointe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class PieceJointeDaoToDtoMapper {

    @Mapping(target = "name", source = "nom")
    @Mapping(target = "content", source = "contenu")
    @Mapping(target = "length", source = "taille")
    public abstract PieceJointe daoToDto(PieceJointeDao pieceJointeDao);

    @Named("mapList")
    public abstract List<PieceJointe> mapList(List<PieceJointeDao> pieceJointeDaos);
}
