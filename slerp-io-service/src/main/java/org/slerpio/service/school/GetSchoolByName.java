package org.slerpio.service.school;

import org.slerp.core.business.DefaultBusinessFunction;
import org.slerpio.entity.School;
import org.springframework.stereotype.Service;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.Domain;
import org.slerpio.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
@KeyValidation({ "schoolName", "page", "size" })
@NumberValidation()
@NotBlankValidation({ "page", "size" })
public class GetSchoolByName extends DefaultBusinessFunction {

	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public Domain handle(Domain schoolDomain) {
		int page = schoolDomain.getInt("page");
		int size = schoolDomain.getInt("size");
		Page<School> schoolPage = schoolRepository.getSchoolByName(schoolDomain.getString("schoolName").toUpperCase(),
				new PageRequest(page, size));
		return new Domain().put("schoolPage", schoolPage);
	}
}