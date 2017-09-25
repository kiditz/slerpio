package org.slerpio.service.profile;

import static org.slerpio.SlerpIOConstant.BASE_DIR;
import static org.slerpio.SlerpIOConstant.Exception.PROFILE_NOT_FOUND;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.utils.Base64Coder;
import org.slerp.core.validation.KeyValidation;
import org.slerpio.SlerpIOConstant;
import org.slerpio.entity.Profile;
import org.slerpio.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "username", "picture" })
public class EditPhotoProfile extends DefaultBusinessTransaction {

	@Autowired
	ProfileRepository profileRepository;
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void prepare(Domain profileDomain) throws Exception {
		log.debug("Input : {}", profileDomain);
		// Extract domain to variable
		String username = profileDomain.getString("username");
		String data = profileDomain.getString("picture");
		String filename = "profile." + getExt(data);
		Profile profile = profileRepository.findProfileByUsername(username);
		if (profile == null) {
			throw new CoreException(PROFILE_NOT_FOUND);
		}

		// Write photo profile

		BufferedImage image = ImageIO.read(getBase64(data));
		File outputFile = new File(BASE_DIR, username.concat(File.separator).concat(filename));
		if(!outputFile.getParentFile().isDirectory())
			if(!outputFile.getParentFile().mkdirs())
				throw new CoreException(SlerpIOConstant.Exception.FAILED_TO_WRITE_DIRECTORY);
				
		log.debug("File Output : {}", outputFile);
		try {
			ImageIO.write(image, getExt(data), outputFile);
		} catch (Exception e) {
			log.error("Exception : {}", e);
			throw new CoreException(SlerpIOConstant.Exception.FAILED_TO_WRITE_FILE);
		}
		profile.setLastUpdate(new Date());
		profile.setImagePath(filename);
		profileDomain.put("profile", profile);
	}

	@Override
	public Domain handle(Domain profileDomain) {
		super.handle(profileDomain);
		try {
			Profile profile = profileDomain.getDomain("profile").convertTo(Profile.class);
			profile = profileRepository.save(profile);
			return new Domain(profile);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}

	private String getExt(String data) {
		int beginIndex = data.indexOf('/');
		int endIndex = data.indexOf(';');
		String ext = data.substring(beginIndex + 1, endIndex);
		return ext;
	}

	private ByteArrayInputStream getBase64(String data) {
		int beginIndex = data.indexOf(',');
		int endIndex = data.length();
		String base64 = data.substring(beginIndex + 1, endIndex);
		return new ByteArrayInputStream(Base64Coder.decode(base64));
	}
}