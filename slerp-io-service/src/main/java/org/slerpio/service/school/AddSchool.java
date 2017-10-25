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
import org.slerp.core.business.DefaultBusinessTransaction;

@Service
@Transactional
@KeyValidation({"name", "description", "address", "latitude", "longitude",
		"active", "activeAt", "createdAt", "updateAt"})
@NotBlankValidation({"name", "active", "activeAt", "createdAt"})
public class AddSchool extends DefaultBusinessTransaction {

	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public void prepare(Domain schoolDomain) throws Exception {
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