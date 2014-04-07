package test;

import org.hsqldb.util.DatabaseManagerSwing;



public class Main {

	public static void main(String[] args) {

		// Get the spring configuration
		DatabaseManagerSwing mgr = new DatabaseManagerSwing();
		mgr.main();
		System.out.println("Finished creating database manager");
	}

}
