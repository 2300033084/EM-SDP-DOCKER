package klu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import klu.Model.Employee;
import klu.Model.Manager;
import klu.Service.EmployeeService;
import klu.Service.ManagerService;
import klu.enums.Status;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;
    
    @Autowired
    private ManagerService managerService;

    @PostMapping("/addEmployee") 
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee, @RequestParam Long managerId) {
        try {
            // Step 1: Fetch Manager from DB using the correct service
            Manager manager = managerService.findById(managerId);
            if (manager == null) {
                return new ResponseEntity<>("Manager not found with ID: " + managerId, HttpStatus.NOT_FOUND);
            }
            
            // Step 2: Set Status to ACCEPTED and map to Manager before Saving
            employee.setStatus(Status.ACCEPTED);
            employee.setManager(manager);
            System.out.println("Debug: Employee manager set -> " + employee.getManager().getId());

            // Step 3: Save Employee
            service.addEmployee(employee);
            return new ResponseEntity<>("Employee added successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allEmployees")
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }
    
    @GetMapping("/byManager/{managerId}")
    public List<Employee> getEmployeesByManager(@PathVariable Long managerId) {
        return service.getEmployeesByManager(managerId);
    }
}
