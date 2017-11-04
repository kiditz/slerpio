package org.slerpio.service.school;

import org.slerp.core.CoreException;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerpio.ServiceConstant;
import org.slerpio.entity.School;
import org.springframework.stereotype.Service;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.Domain;
import org.slerpio.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@KeyValidation("schoolId")
@NumberValidation({"schoolId"})
@NotBlankValidation({})
public class FindSchoolById extends DefaultBusinessFunction {

	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public Domain handle(Domain schoolDomain) {
		School school = schoolRepository.findOne(schoolDomain.getLong("schoolId"));
		if(school == null){
			throw new CoreException(ServiceConstant.SCHOOL_NOT_FOUND);
		}
		return new Domain(school);
	}
}