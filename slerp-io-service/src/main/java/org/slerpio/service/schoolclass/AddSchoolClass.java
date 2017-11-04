package org.slerpio.service.schoolclass;

import java.util.Date;

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
@KeyValidation({ "name", "code", "userProfileId", "updateAt", "createdAt"})
@NotBlankValidation({ "name", "code", "userProfileId" })
@NumberValidation("userProfileId")
public class AddSchoolClass extends DefaultBusinessTransaction {

	@Autowired
	SchoolClassRepository schoolClassRepository;
	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	public void prepare(Domain schoolClassDomain) throws Exception {
		Long profileId = schoolClassDomain.getLong("userProfileId");
		if (!userProfileRepository.exists(profileId)) {
			throw new CoreException(ServiceConstant.PROFILE_NOT_FOUND);
		}
		UserProfile userProfile = userProfileRepository.findProfileById(profileId);
		schoolClassDomain.put("userProfileId", userProfile);
	}

	@Override
	public Domain handle(Domain schoolClassDomain) {
		super.handle(schoolClassDomain);
		try {
			SchoolClass schoolClass = schoolClassDomain.convertTo(SchoolClass.class);
			schoolClass = schoolClassRepository.save(schoolClass);
			return new Domain(schoolClass);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}