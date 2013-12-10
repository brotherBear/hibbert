package test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.IllegalTransactionStateException;

import test.domain.Employee;
import test.domain.Project;
import test.service.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/repositories.xml" })
public class ProjectRepositoryTest {

	@Autowired
	private GenericRepository<Project, Project> projectRepository;

	@Autowired
	private ProjectService projectService;

	@Before
	public void insertData() {
		assertNotNull(projectRepository);
		assertNotNull(projectService);
	}

	@After
	public void removeData() {
		Collection<Project> projects = projectService.findAll();
		for (Project project : projects) {
			projectService.removeProject(project);
		}
	}

	@BeforeClass
	public static void setup() {
		// Un-comment to check the contents of the database (useful in debug)
		// org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--noexit" });
	}

	@Test
	public void createProject() {
		Employee manager = Employee.create("Elmer Fudd");
		String projectName = "Humpty Dumpty";
		Project newInstance = projectService.createProject(projectName, manager);

		Project other = projectService.findProject(projectName);
		assertEquals(other.getName(), newInstance.getName());
	}

	@Test
	public void createThenUpdateProject() {
		Employee manager = Employee.create("Terry");
		String projectName = "Life of Brian";
		Project p = projectService.createProject(projectName, manager);

		p.addMinion(Employee.create("John"));
		p.addMinion(Employee.create("Michael"));

		projectService.updateProject(p);

		Project other = projectService.findProject(projectName);
		assertEquals(other.getName(), p.getName());
	}

	@Test
	public void createProjectAndAssignPeople() {
		Employee manager = Employee.create("Terry");
		String projectName = "The Meaning of Liff";
		Project p = projectService.createProject(projectName, manager);

		p = projectService.assignToProject(p, Employee.create("John"));
		p = projectService.assignToProject(p, Employee.create("Michael"));
		p = projectService.assignToProject(p, Employee.create("Graham"));

		Project other = projectService.findProject(projectName);
		assertEquals(other.getName(), p.getName());
		assertEquals(3, p.getMinions().size());

		p.addMinion(Employee.create("Polly the Parrot"));
		assertEquals(4, p.getMinions().size());
		assertEquals(3, other.getMinions().size());
	}

	@Test
	public void checkThatAccessingRepositoryThrowsException() {
		assertNotNull(projectRepository);
		try {
			projectRepository.create(Project.create(Employee.create("Harry Hole"), "Politiskolen III"));
			fail("Throw a Transaction exception");
		} catch (IllegalTransactionStateException e) {
		}
	}

	// TODO Populate a collection, and load it lazily
}
