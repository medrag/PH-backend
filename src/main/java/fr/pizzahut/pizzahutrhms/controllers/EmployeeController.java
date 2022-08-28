package fr.pizzahut.pizzahutrhms.controllers;

import fr.pizzahut.pizzahutrhms.models.dao.EmployeeDao;
import fr.pizzahut.pizzahutrhms.models.dto.Company;
import fr.pizzahut.pizzahutrhms.models.dto.Employee;
import fr.pizzahut.pizzahutrhms.models.dto.PieceJointe;
import fr.pizzahut.pizzahutrhms.services.EmployeeService;
import fr.pizzahut.pizzahutrhms.services.PieceJointeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PieceJointeService pieceJointeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, PieceJointeService pieceJointeService) {
        this.employeeService = employeeService;
        this.pieceJointeService = pieceJointeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveNewEmployee(@RequestPart("employee") Employee employee,
                                                @RequestPart(value = "pieceJointe", required = false) List<MultipartFile> piecesJointes) {
        EmployeeDao savedEmployee = this.employeeService.saveNewEmployee(employee);
        this.pieceJointeService.savePjs(piecesJointes, savedEmployee);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editEmployee(@RequestPart("employee") Employee employee,
                                             @RequestPart(value = "pieceJointe", required = false) List<MultipartFile> piecesJointes) {
        EmployeeDao updatedEmployee = this.employeeService.editEmployee(employee);
        this.pieceJointeService.editPjs(piecesJointes, updatedEmployee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteEmployee(@RequestBody Employee employee) {
        this.employeeService.deleteEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deactivate")
    public ResponseEntity<Void> deactivateEmployee(@RequestPart("employee") Employee employee,
                                                   @RequestPart(value = "pieceJointe", required = false) List<MultipartFile> piecesJointes) {

        EmployeeDao employeeToDeactivate = this.employeeService.deactivateEmployee(employee);
        this.pieceJointeService.savePjs(piecesJointes, employeeToDeactivate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{emp_id}/pieces-jointes")
    public ResponseEntity<List<PieceJointe>> getEmployeePjs(@PathVariable(value = "emp_id") Long empId) {
        return ResponseEntity.ok(this.pieceJointeService.getEmployeePjs(empId));
    }
}
