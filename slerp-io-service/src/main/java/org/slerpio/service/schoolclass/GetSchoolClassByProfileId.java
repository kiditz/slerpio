package org.slerpio.service.schoolclass;

import org.slerp.core.business.DefaultBusinessFunction;
import org.slerpio.entity.SchoolClass;
import org.springframework.stereotype.Service;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.Domain;
import org.slerpio.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@KeyValidation("profileId")
@NumberValidation({})
@NotBlankValidation({})
public class GetSchoolClassByProfileId extends DefaultBusinessFunction {

	@Autowired
	SchoolClassRepository schoolClassRepository;

	@Override
	public Domain handle(Domain schoolClassDomain) {
		List<SchoolClass> schoolClass = schoolClassRepository
				.findSchoolClassByProfileId(schoolClassDomain
						.getLong("profileId"));
		return new Domain().put("classList", schoolClass);
	}
}