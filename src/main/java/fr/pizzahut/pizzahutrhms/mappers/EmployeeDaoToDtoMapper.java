package fr.pizzahut.pizzahutrhms.mappers;

import fr.pizzahut.pizzahutrhms.models.dao.EmployeeDao;
import fr.pizzahut.pizzahutrhms.models.dto.Employee;
import org.apache.logging.log4j.util.Strings;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring", uses = CompanyDaoToDtoMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class EmployeeDaoToDtoMapper {

    @Mapping(target = "jrsSemaine", ignore = true)
    public abstract Employee daoToDto(EmployeeDao employeeDao);

    @Named("mapList")
    public abstract List<Employee> mapList(List<EmployeeDao> employeeDaos);

    @AfterMapping
    public void processJrsSemaineString(EmployeeDao source, @MappingTarget Employee employee) {
        if (Strings.isNotEmpty(source.getJrsSemaine())) {
            employee.setJrsSemaine(Stream.of(source.getJrsSemaine().split(","))
                    .map(String::trim)
                    .collect(Collectors.toList()));
        }
    }
}
