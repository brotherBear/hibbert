package test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.domain.Employee;
import test.service.EmployeeManagementService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/repositories.xml" })
public class EmployeeManagementServiceTest {
	
	@Autowired
	EmployeeManagementService service;
	private static final String EMP_NAME1 = "Mr Bean";
	private static final String EMP_NAME2 = "Mr Gumby";

	@Before
	public void setUp() {
		assertNotNull(service);
		Collection<Employee> emps = service.findEmployees();
		for (Employee employee : emps) {
			service.remove(employee);
		}

		service.add(Employee.create(EMP_NAME1));
		service.add(Employee.create(EMP_NAME2));
	}
	
	@After
	public void clean()  {
		Collection<Employee> emps = service.findEmployees();
		for (Employee employee : emps) {
			service.remove(employee);
		}
	}
	
	@Test
	public void testFindAll() {
		Collection<Employee> list = service.findEmployees();
		assertEquals(2, list.size());
	}
	
	@Test
	public void testFindAllManagers(){
		Employee emp = service.findEmployee(EMP_NAME1);
		service.assignManager("Overhaul", emp);
		Collection<Employee> list = service.findManagers();
		assertEquals(1, list.size());
	}
	

}
