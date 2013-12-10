package test.service;

import java.util.Collection;

import test.domain.Employee;


public interface EmployeeManagementService {

	Employee findEmployee(String name);
	Collection<Employee> findManagers();
	Collection<Employee> findEmployees();
	void add(Employee newEmployee);
	void remove(Employee exEmployee);
	Boolean hasEmployee(Employee candidate);
	void assignManager(String string, Employee emp);
}
