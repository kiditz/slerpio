package org.slerpio.service.profile;

import java.util.List;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.entity.UserProfile;
import org.slerpio.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation({ "classId", "fullname" })
@NumberValidation({ "classId" })
@NotBlankValidation({ "classId", "fullname" })
public class GetStudentNotHaveClass extends DefaultBusinessFunction {

	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	public Domain handle(Domain userProfileDomain) {
		Long classId = userProfileDomain.getLong("classId");
		String fullname = userProfileDomain.getString("fullname");
		List<UserProfile> userProfileList = userProfileRepository.getStudentNotHaveClass(classId, fullname);
		return new Domain().put("students", userProfileList);
	}
}