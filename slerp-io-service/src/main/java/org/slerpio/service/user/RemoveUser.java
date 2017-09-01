package org.slerpio.service.user;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.KeyValidation;
import org.slerpio.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "username", "type" })
public class RemoveUser extends DefaultBusinessTransaction {

	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	DataSource dataSource;
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void prepare(Domain inputDomain) throws Exception {
	}

	@Override
	public Domain handle(Domain inputDomain) {
		super.handle(inputDomain);
		try {
			JdbcTemplate template = new JdbcTemplate(dataSource);
			SimpleJdbcCall call = new SimpleJdbcCall(template).withFunctionName("deleteUser");
			template.setResultsMapCaseInsensitive(true);
			log.info("Removing user with name {}", inputDomain.getString("username"));
			Map<String, Object> userDomain = new HashMap<>();
			userDomain.put("typename", inputDomain.getString("type"));
			userDomain.put("name", inputDomain.getString("username"));
			call.executeFunction(Void.class, userDomain);
			return new Domain().put("success", true);
		} catch (Exception e) {
			return new Domain().put("success", false);
		}
	}
}
