package org.slerpio.service.profile;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.ServiceConstant;
import org.slerpio.entity.School;
import org.slerpio.entity.UserProfile;
import org.slerpio.repository.SchoolRepository;
import org.slerpio.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "phoneNumber", "fullname", "gender", "address", "latitude", "longitude", "active", "activeAt",
		"createdAt", "updateAt" })
@NotBlankValidation({ "phoneNumber", "fullname", "gender", "active", "activeAt", "createdAt" })
public class AddUserProfile extends DefaultBusinessTransaction {

	@Autowired
	UserProfileRepository userProfileRepository;
	@Autowired
	SchoolRepository schoolRepository;
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void prepare(Domain userProfileDomain) throws Exception {
		if (userProfileDomain.containsKey("schoolId")) {
			Domain schoolDomain = userProfileDomain.getDomain("schoolId");
			School schoolId = schoolDomain.convertTo(School.class);
			schoolId = schoolRepository.findOne(schoolId.getSchoolId());
			if (schoolId == null) {
				throw new CoreException("school.not.found");
			}
			userProfileDomain.put("schoolId", new Domain(schoolId));
		}
		String phoneNumber = userProfileDomain.getString("phoneNumber");
		log.info("Phone Number >>> {}", phoneNumber);
		log.info("Exists >>> {}",userProfileRepository.isExistsByPhoneNumber(phoneNumber));
		if (userProfileRepository.isExistsByPhoneNumber(phoneNumber)) {
			throw new CoreException(ServiceConstant.PHONE_NUMBER_EXISTS);
		}
	}

	@Override
	public Domain handle(Domain userProfileDomain) {
		super.handle(userProfileDomain);
		UserProfile userProfile = userProfileDomain.convertTo(UserProfile.class);
		if (userProfileDomain.containsKey("schoolId")) {
			School school = userProfileDomain.getDomain("schoolId").convertTo(School.class);
			userProfile.addSchool(school);
		}
		log.info("Input >>> {}", userProfileDomain);
		userProfile = userProfileRepository.save(userProfile);
		return new Domain(userProfile);
	}
}