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
import org.springframework.transaction.annotation.Transactional;

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

	private final Employee john = Employee.create("John");

	private final Employee michael = Employee.create("Michael");

	private final Employee graham = Employee.create("Graham");

	private final Employee terry = Employee.create("Terry");

	private final Employee polly = Employee.create("Polly the Parrot");

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
		String projectName = "Life of Brian";
		Project p = projectService.createProject(projectName, terry);

		p.addMinion(john);
		p.addMinion(michael);

		projectService.updateProject(p);

		Project other = projectService.findProject(projectName);
		assertEquals(other.getName(), p.getName());
	}

	@Test
	@Transactional
	public void createProjectAndAssignPeopleInsideTransaction() {
		String projectName = "The Meaning of Liff";
		Project p = projectService.createProject(projectName, terry);

		p = projectService.assignToProject(p, john);
		p = projectService.assignToProject(p, michael);
		p = projectService.assignToProject(p, graham);

		Project other = projectService.findProject(projectName);
		assertEquals(other.getName(), p.getName());
		assertEquals(other, p);
		assertEquals(3, p.getMinions().size());

		p.addMinion(Employee.create("Polly the Parrot"));
		assertEquals(4, p.getMinions().size());
		/*
		 * In this case both p and 'other' is attached to the persistence context, and updates in one is reflected in
		 * the other.
		 */
		assertEquals(4, other.getMinions().size());
		p.removeMinion(john);
		assertEquals(3, p.getMinions().size());
		assertEquals(3, other.getMinions().size());
	}

	@Test
	public void createProjectAndAssignPeople() {
		String projectName = "The Meaning of Liff";
		Project p = projectService.createProject(projectName, terry);

		p = projectService.assignToProject(p, john);
		p = projectService.assignToProject(p, michael);
		p = projectService.assignToProject(p, graham);

		// Load project 'other' with employees
		Project other = projectService.findProjectWithEmployees(projectName);
		assertEquals(other.getName(), p.getName());
		assertEquals(other, p);
		assertEquals(3, p.getMinions().size());

		p.addMinion(polly);
		assertEquals(4, p.getMinions().size());
		/*
		 * In this case the 'other' is detached from persistence context, and updates to p will not reflect in other.
		 */
		assertEquals(3, other.getMinions().size());
		p.removeMinion(john);
		assertEquals(3, p.getMinions().size());
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

}
