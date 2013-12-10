package test.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.domain.Employee;
import test.domain.Project;
import test.repository.GenericRepository;
import test.util.HibernateUtil;

@Transactional
@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {
	@Autowired
	HibernateUtil util;
	@Autowired
	GenericRepository<Employee, Employee> employeeRepository;
	@Autowired
	GenericRepository<Project, Project> projectRepository;

	public Employee findEmployee(String name) {
		return employeeRepository.findByName(name);
	}

	public Collection<Employee> findManagers() {
		Set<Employee> res = new HashSet<Employee>();
		Collection<Employee> emps = findEmployees();
		for (Employee employee : emps) {
			if (employee.isManager()) res.add(employee);
		}
		return res;
	}

	public Collection<Employee> findEmployees() {
		return employeeRepository.findAll();
	}

	public void add(Employee newEmployee) {
		employeeRepository.create(newEmployee);
	}

	public void remove(Employee exEmployee) {
		employeeRepository.delete(exEmployee);
	}

	public Boolean hasEmployee(Employee candidate) {
		Employee e = employeeRepository.findByName(candidate.getName());
		return e != null;
	}

	public void assignManager(String projectName, Employee emp) {
		Project proj = projectRepository.findByName(projectName);
		if (proj == null) {
			proj = Project.create(emp, projectName);
		}
		emp.manage(proj);
		employeeRepository.update(emp);
	}

}
