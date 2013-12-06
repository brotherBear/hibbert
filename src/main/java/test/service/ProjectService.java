package test.service;

import test.domain.Employee;
import test.domain.Project;



public interface ProjectService {

	Project createProject(String name, Employee manager);
	Project findProject(String name);
	Project findProjectFor(Employee manager);

	void changeManager(Project p, Employee newManager);
	void assignToProject(Project p, Employee minion);
	void removeFromProject(Project p, Employee exMinion);
	void removeProject(Project p);
	void updateProject(Project p);

}
