package common;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class SchemaGenerator implements InitializingBean{

	
	public static final String SCHEMA = "hibbert";
	
	@Autowired
	private DataSource dataSource;

	public void afterPropertiesSet() throws Exception {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		template.execute("Create schema " + SCHEMA + " authorization dba");
	}

	
}
