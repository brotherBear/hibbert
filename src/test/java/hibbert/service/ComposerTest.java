package hibbert.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hibbert.domain.Product;
import hibbert.domain.Part;
import hibbert.domain.SpecialPart;

import java.util.Collection;

import org.junit.After;
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
	
	@After
	public void removeProducts() {
		composer.clear();
	}

	@Test
	public void testProduct() {
		assertNotNull("Need a Composer instance", composer);
		Product first = composer.generate();
		composer.save(first);
		Collection<Product> products = composer.fetchAll();
		assertTrue("At least one element in collection", products.size() >= 1);
	}

	@Test
	public void testOverall() {
		assertNotNull("Need a Composer instance", composer);
		Product first = composer.generate();
		composer.save(first);
		Product second = composer.generateWithSubtype();
		composer.save(second);
		Collection<Product> all = composer.fetchAll();
		assertTrue(all.size() >= 2);

		int countC = 0;
		int countB = 0;
		for (Product a : all) {
			Collection<Part> coll = a.getParts();
			for (Part b : coll) {
				// Make sure we have a 'real' object here since we're testing by instanceof
				b = HibernateUtil.unproxy(b);

				if (b instanceof SpecialPart) {
					countC++;
				} else {
					countB++;
				}
			}
		}

		assertTrue("Expect (n > 0) objects of type C, but found " + countC, countC > 0);
		assertTrue("Expect (n > 0) objects of type B, but found " + countB, countB > 0);
	}

	@Test
	public void testCollectionOfParts() {
		assertNotNull("Need a Composer instance", composer);
		composer.save(composer.generate());
		composer.save(composer.generateWithSubtype());

		Collection<Part> bs = composer.findAllParts();
		assertTrue(bs.size() > 0);
	}

	@Test
	public void testCollectionOfSpecialParts() {
		assertNotNull("Need a Composer instance", composer);
		composer.save(composer.generate());
		composer.save(composer.generateWithSubtype());

		Collection<SpecialPart> cs = composer.findAllSpecialParts();
		assertTrue(cs.size() > 0);
	}

}
