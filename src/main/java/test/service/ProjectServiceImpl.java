package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.domain.Employee;
import test.domain.Project;
import test.repository.GenericRepository;

@Transactional
@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	GenericRepository<Project, Project> projectRepository;
	@Autowired
	GenericRepository<Employee, Employee> employeeRepository;

	public Project createProject(String name, Employee manager) {
		Project p = Project.create(manager, name);
		projectRepository.create(p);
		return p;
	}

	public void changeManager(Project p, Employee newManager) {
		p.setManager(newManager);
		projectRepository.update(p);
	}

	public void assignToProject(Project p, Employee minion) {
		p.addMinion(minion);
		projectRepository.update(p);
	}

	public void removeFromProject(Project p, Employee exMinion) {
		p.removeMinion(exMinion);
		projectRepository.update(p);
	}

	public void removeProject(Project p) {
		// TODO Auto-generated method stub

	}

}
