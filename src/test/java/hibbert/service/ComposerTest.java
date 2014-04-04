package hibbert.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import hibbert.domain.A;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/appContext.xml" })
public class ComposerTest {

	@Autowired
	Composer c;

	@Test
	public void test() {
		assertNotNull("Need a Composer instance", c);
		A first = c.generate();
		c.save(first);
		A second = c.generateWithSuptype();
		c.save(second);
		Collection<A> all = c.fetchAll();
		assertEquals(2, all.size());

		// fail("Not yet implemented");
	}

}
