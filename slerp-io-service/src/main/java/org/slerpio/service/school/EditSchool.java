package org.slerpio.service.school;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.slerpio.repository.SchoolRepository;
import org.slerp.core.Domain;
import org.slerpio.entity.School;
import org.slerp.core.CoreException;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.business.DefaultBusinessTransaction;

@Service
@Transactional
@KeyValidation({ "schoolId", "name", "address", "description", "latitude", "longitude" })
@NotBlankValidation({ "name" })
@NumberValidation("schoolId")
public class EditSchool extends DefaultBusinessTransaction {

	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public void prepare(Domain schoolDomain) throws Exception {
		School school = schoolRepository.findOne(schoolDomain.getLong("schoolId"));
		if (school == null) {
			throw new CoreException("org.slerpio.entity.School.notFound");
		}
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