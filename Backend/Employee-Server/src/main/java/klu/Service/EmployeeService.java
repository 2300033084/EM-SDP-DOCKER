package klu.Service;

import java.util.List;
import klu.Model.Employee;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.Model.Manager;
import klu.Model.Employee;
import klu.Repository.EmployeeRepo;
import klu.Repository.ManagerRepo;
import klu.enums.Status;

@Service
public class EmployeeService {
	@Autowired
    private EmployeeRepo repo;
	@Autowired
	private ManagerRepo mrepo;
	@Autowired
    private EmailService emailService;
    public void addEmployee(Employee employee) {
        repo.save(employee);
    }

    public List<Employee> getEmployees() {
        return repo.findAll();
    }
    public List<Employee> getEmployeesByManager(Long managerId) {
        return repo.findByManagerId(managerId);
    }
    public Employee updateEmployeeStatus(Long id, Status status) {  // Change String to Status
        Employee employee = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setStatus(status);  // Directly setting Status enum
        return repo.save(employee);
    }

	public Manager findManagerById(Long managerId) {
			// TODO Auto-generated method stub
			return mrepo.findById(managerId).orElse(null);
			
		}
	public Employee findByEmail(String email) {
	    return repo.findByEmail(email);
	}


	
}


