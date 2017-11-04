package org.slerpio.service.schoolclass;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.entity.UserProfile;
import org.slerpio.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@KeyValidation({"schoolClassId", "page", "size"})
@NumberValidation({})
@NotBlankValidation({})
public class GetStudentFromClassId extends DefaultBusinessFunction {

	@Autowired
	SchoolClassRepository schoolClassRepository;

	@Override
	public Domain handle(Domain schoolClassDomain) {
		int page = schoolClassDomain.getInt("page");
		int size = schoolClassDomain.getInt("size");
		Page<UserProfile> schoolClassPage = schoolClassRepository
				.getStudentFromClassId(
						schoolClassDomain.getLong("schoolClassId"),
						new PageRequest(page, size));
		return new Domain().put("schoolClassPage", schoolClassPage);
	}
}