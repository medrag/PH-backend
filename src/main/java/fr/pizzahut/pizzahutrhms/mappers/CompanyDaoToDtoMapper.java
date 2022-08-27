package fr.pizzahut.pizzahutrhms.mappers;

import fr.pizzahut.pizzahutrhms.models.dao.CompanyDao;
import fr.pizzahut.pizzahutrhms.models.dto.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CompanyDaoToDtoMapper {

    public abstract Company daoToDto(CompanyDao companyDao);

    @Named("mapList")
    public abstract List<Company> mapList(List<CompanyDao> companyDaos);
}
