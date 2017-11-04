package org.slerpio.service.profile;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.ServiceConstant;
import org.slerpio.entity.SchoolClass;
import org.slerpio.entity.UserProfile;
import org.slerpio.repository.SchoolClassRepository;
import org.slerpio.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@KeyValidation({ "profileId", "classId", })
@NotBlankValidation({ "profileId", "classId", })
@NumberValidation({ "profileId", "classId", })
public class AddStudentToClass extends DefaultBusinessTransaction {

	@Autowired
	SchoolClassRepository schoolClassRepository;
	@Autowired
	UserProfileRepository profileRepository;
	@Override
	public void prepare(Domain schoolClassDomain) throws Exception {
		Long profileId = schoolClassDomain.getLong("profileId");
		Long classId = schoolClassDomain.getLong("classId");
		SchoolClass schoolClass = schoolClassRepository.findOne(classId);

		if(schoolClass == null)
			throw new CoreException(ServiceConstant.SCHOOL_NOT_FOUND);
		schoolClassDomain.put("class", schoolClass);
		UserProfile profile = profileRepository.findOne(profileId);
		if(profile == null)
			throw new CoreException(ServiceConstant.PROFILE_NOT_FOUND);
		schoolClassDomain.put("profile", profile);
	}

	@Override
	public Domain handle(Domain schoolClassDomain) {
		super.handle(schoolClassDomain);
		UserProfile profile = schoolClassDomain.getDomain("profile").convertTo(UserProfile.class);
		SchoolClass schoolClass = schoolClassDomain.getDomain("class").convertTo(SchoolClass.class);
		schoolClass.addStudent(profile);
		schoolClass = schoolClassRepository.save(schoolClass);
		return new Domain(schoolClass);
	}
}