package hibbert.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hibbert.domain.A;
import hibbert.domain.B;
import hibbert.domain.C;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.util.HibernateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/appContext.xml" })
public class ComposerTest {

	@Autowired
	Composer composer;

	@Test
	public void testOverall() {
		assertNotNull("Need a Composer instance", composer);
		A first = composer.generate();
		composer.save(first);
		A second = composer.generateWithSubtype();
		composer.save(second);
		Collection<A> all = composer.fetchAll();
		assertTrue(all.size() >= 2);

		int countC = 0;
		int countB = 0;
		for (A a : all) {
			Collection<B> coll = a.getBs();
			for (B b : coll) {
				b = HibernateUtil.unproxy(b);
				
				if (b instanceof C) {
					System.out.println("Found a C! " + b.toString());
					countC++;
				} else {
					System.out.println("Found a B! " + b.toString());
					countB++;
				}
			}
		}

		assertTrue("Expect (n > 0) objects of type C, but found " + countC, countC > 0);
		assertTrue("Expect (n > 0) objects of type B, but found " + countB, countB > 0);
	}

	@Test
	public void testCollectionOfBs() {
		assertNotNull("Need a Composer instance", composer);
		composer.save(composer.generate());
		composer.save(composer.generateWithSubtype());

		Collection<B> bs = composer.findAllBs();
		assertTrue(bs.size() > 0);
	}

	@Test
	public void testCollectionOfCs() {
		assertNotNull("Need a Composer instance", composer);
		composer.save(composer.generate());
		composer.save(composer.generateWithSubtype());

		Collection<C> cs = composer.findAllCs();
		assertTrue(cs.size() > 0);
	}

}
