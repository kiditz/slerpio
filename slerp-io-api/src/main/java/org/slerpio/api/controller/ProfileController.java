package org.slerpio.api.controller;

import static org.slerpio.SlerpIOConstant.BASE_DIR;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slerp.core.Domain;
import org.slerp.core.business.BusinessFunction;
import org.slerp.core.business.BusinessTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

	@Autowired
	BusinessTransaction addProfile;
	@Autowired
	BusinessFunction findProfileByUsername;
	@Autowired
	BusinessTransaction editProfile;
	@Autowired
	BusinessTransaction editPhotoProfile;
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	KafkaTemplate<String, Domain> kafkaTemplate;

	@Value("${kafka.messages.edit_user}")
	private String editUser;
	@Autowired
	private BusinessFunction isProfileExistByUsername;

	@PostMapping("/profile")
	@ResponseBody
	public Domain addProfile(@RequestBody Domain profileDomain) {
		log.debug("Reuest Add Profile : {}", profileDomain);
		Domain outputDto = addProfile.handle(profileDomain);
		log.debug("Response Add Profile : {}", outputDto);
		return outputDto;
	}

	@GetMapping("/profile")
	@ResponseBody
	public Domain findProfileByUsername(@RequestParam("username") String username) {
		log.debug("Username : {}", username);
		Domain profileDomain = new Domain();
		profileDomain.put("username", username);
		Domain outputDto = findProfileByUsername.handle(profileDomain);
		log.debug("Response Find Profile : {}", outputDto);
		return outputDto;
	}
	@GetMapping("/username-exists")
	@ResponseBody
	public Domain isProfileExistByUsername(@RequestParam("username") String username) {
		log.debug("Username : {}", username);
		Domain profileDomain = new Domain();
		profileDomain.put("username", username);
		Domain outputDto = isProfileExistByUsername.handle(profileDomain);
		log.debug("Response Find Profile : {}", outputDto);
		return outputDto;
	}
	@PostMapping("/photo_profile")
	@ResponseBody
	public Domain editPhotoProfile(@RequestBody Domain profileDomain) {
		log.debug("Request Edit Photo Profile : {}", profileDomain);
		Domain outputDto = editPhotoProfile.handle(profileDomain);
		log.debug("Response Edit Photo Profile : {}", outputDto);
		return outputDto;
	}

	@PutMapping("/profile")
	@ResponseBody
	public Domain editProfile(@RequestBody Domain profileDomain) {
		log.debug("Reuest Edit Profile : {}", profileDomain);
		Domain outputDto = editProfile.handle(profileDomain);
		log.debug("Response Edit Profile : {}", outputDto);
		profileDomain.remove("profile");
		kafkaTemplate.send(editUser, profileDomain);
		return outputDto;
	}

	@GetMapping(value = "/profile-image", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_GIF_VALUE })
	public byte[] renderImage(String username) throws IOException {
		File inputFile = new File(BASE_DIR, username.concat(File.separator).concat("profile.png"));
		byte[] bytes = StreamUtils.copyToByteArray(new FileInputStream(inputFile));
		return bytes;
	}
}