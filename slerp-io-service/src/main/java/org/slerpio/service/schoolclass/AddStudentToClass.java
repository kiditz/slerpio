package org.slerpio.service.schoolclass;

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
	}

	@Override
	public Domain handle(Domain schoolClassDomain) {
		super.handle(schoolClassDomain);
		Long profileId = schoolClassDomain.getLong("profileId");
		Long classId = schoolClassDomain.getLong("classId");
		SchoolClass schoolClass = schoolClassRepository.findOne(classId);

		if(schoolClass == null)
			throw new CoreException(ServiceConstant.SCHOOL_NOT_FOUND);
		UserProfile profile = profileRepository.findOne(profileId);
		if(profile == null)
			throw new CoreException(ServiceConstant.PROFILE_NOT_FOUND);
		schoolClass.addStudent(profile);
		schoolClass = schoolClassRepository.saveAndFlush(schoolClass);
		return new Domain(schoolClass);
	}
}