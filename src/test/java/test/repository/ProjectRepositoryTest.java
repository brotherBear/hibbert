package test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

	@BeforeClass
	public static void setup() {
		// Uncomment to check the contents of the database (useful in debug)
		//org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--noexit" });
	}

	@Test
	public void test() {
		assertNotNull(projectRepository);
		assertNotNull(projectService);
	}

	@Test
	public void createProject() {
		Employee manager = Employee.create("Elmer Fudd");
		Project newInstance = projectService.createProject("Humpty Dumpty", manager);

		Project other = projectService.findProject("Humpty Dumpty");
		assertEquals(other, newInstance);
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
		assertEquals(other, p);
	}
	
	//TODO Populate a collection, and load it lazily 
}
