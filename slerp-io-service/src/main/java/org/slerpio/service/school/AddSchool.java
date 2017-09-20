package org.slerpio.service.school;

import java.util.Date;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.entity.School;
import org.slerpio.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "name", "address", "description", "latitude", "longitude", "buildAt" })
@NotBlankValidation({ "name" })
public class AddSchool extends DefaultBusinessTransaction {

	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public void prepare(Domain schoolDomain) throws Exception {
		schoolDomain.put("createdAt", new Date());
		schoolDomain.put("lastUpdate", new Date());
	}

	@Override
	public Domain handle(Domain schoolDomain) {
		super.handle(schoolDomain);
		try {
			School school = schoolDomain.convertTo(School.class);
			school = schoolRepository.save(school);
			return new Domain(school);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}