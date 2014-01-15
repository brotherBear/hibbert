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

import test.domain.Address;
import test.domain.Country;
import test.domain.Employee;
import test.service.EmployeeManagementService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/appContext.xml" })
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
		Country c = new Country("Norway");
		Country c2 = new Country("UK");
		Address a = new Address("Sesame", c2);
		Address b = new Address("Holmen", c);

		Employee emp1 = Employee.create(EMP_NAME1);
		emp1.addAddress(a);
		service.add(emp1);
		Employee emp2 = Employee.create(EMP_NAME2);
		emp2.addAddress(b);
		service.add(emp2);
		
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
