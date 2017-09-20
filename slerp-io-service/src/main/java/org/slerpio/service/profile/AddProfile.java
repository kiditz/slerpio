package org.slerpio.service.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.slerpio.repository.ProfileRepository;
import org.slerp.core.Domain;
import org.slerpio.entity.Profile;

import java.util.Date;

import org.slerp.core.CoreException;
import org.slerpio.repository.SchoolRepository;
import org.slerpio.entity.School;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.EmailValidation;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.slerpio.SlerpIOConstant.Exception.*;

@Service
@Transactional
@KeyValidation({ "username", "email", "phoneNumber", "fullname", "imagePath", "address", "schoolId" })
@NotBlankValidation({ "username", "email" })
@EmailValidation("email")
public class AddProfile extends DefaultBusinessTransaction {

	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	SchoolRepository schoolRepository;
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void prepare(Domain profileDomain) throws Exception {
		Domain schoolDomain = profileDomain.getDomain("schoolId");
		School schoolId = schoolDomain.convertTo(School.class);
		schoolId = schoolRepository.findOne(schoolId.getSchoolId());
		if (schoolId == null) {
			throw new CoreException(SCHOOL_NOT_FOUND);
		}
		profileDomain.put("createdAt", new Date());
		profileDomain.put("lastUpdate", new Date());
		profileDomain.put("schoolId", new Domain(schoolId));
	}

	@Override
	public Domain handle(Domain profileDomain) {
		super.handle(profileDomain);
		try {
			Profile profile = profileDomain.convertTo(Profile.class);
			log.info("Profile To Put : {}", profile.getSchoolId().getSchoolId());			
			profile = profileRepository.saveAndFlush(profile);
			return new Domain(profile);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}