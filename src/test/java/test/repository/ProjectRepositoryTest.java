package test.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.domain.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/repositories.xml"})
public class ProjectRepositoryTest {

	@Autowired
	private GenericRepository<Project, Project> projectRepository;
	
	@Test
	public void test() {
		assertNotNull(projectRepository);
		fail("Not yet implemented");
	}

}
