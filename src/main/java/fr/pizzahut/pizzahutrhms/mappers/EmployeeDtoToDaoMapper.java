package fr.pizzahut.pizzahutrhms.mappers;

import fr.pizzahut.pizzahutrhms.models.dao.EmployeeDao;
import fr.pizzahut.pizzahutrhms.models.dto.Employee;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CompanyDtoToDaoMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class EmployeeDtoToDaoMapper {

    @Mapping(target = "jrsSemaine", ignore = true)
    @Mapping(target = "piecesJointes", ignore = true)
    @Mapping(target = "company", expression = "java(source.company.isEmpty() ? null : source.company)")
    public abstract EmployeeDao dtoToDao(Employee employee);

    @AfterMapping
    public void processJrsSemaineArray(Employee source, @MappingTarget EmployeeDao target) {
        if (!CollectionUtils.isEmpty(source.getJrsSemaine())) {
            target.setJrsSemaine(source.getJrsSemaine()
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")));
        }
    }
}
