package klu.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import klu.Model.Employee;
import klu.Model.Manager;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
    List<Employee> findByManagerId(Long managerId);

	Employee findByEmail(String email);

}
