package fr.pizzahut.pizzahutrhms.mappers;

import fr.pizzahut.pizzahutrhms.models.dao.CompanyDao;
import fr.pizzahut.pizzahutrhms.models.dto.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CompanyDtoToDaoMapper {
    @Mapping(target = "employees", ignore = true)
    public abstract CompanyDao dtoToDao(Company company);
}
