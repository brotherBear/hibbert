package test;

import test.domain.Employee;
import test.domain.Project;


public class Main {

	public static void main(String[] args) {

		// Get the spring configuration
		
		// Create and manipulate a couple of entities.
		Employee emp1 = Employee.create("Amadeus");
		System.out.println(emp1);
		
		Project p = Project.create(emp1, "Jabba the Hutt");
		System.out.println(p);
	}

}
