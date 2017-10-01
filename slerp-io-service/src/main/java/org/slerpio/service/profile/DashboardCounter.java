package org.slerpio.service.profile;

import java.util.ArrayList;
import java.util.List;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.SlerpIOConstant;
import org.slerpio.repository.ArticleRepository;
import org.slerpio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation("schoolId")
@NumberValidation({})
@NotBlankValidation({})
public class DashboardCounter extends DefaultBusinessFunction {

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	ArticleRepository articleRepository;

	@Override
	public Domain handle(Domain profileDomain) {
		Long countProfileBySchool = profileRepository.countProfileBySchoolId(profileDomain.getLong("schoolId"));
		Long countProfileByTeacher = profileRepository.countProfileByAuthority(SlerpIOConstant.AUTHORITY_TEACHER);
		Long countProfileByStudent = profileRepository.countProfileByAuthority(SlerpIOConstant.AUTHORITY_STUDENT);
		Long countArticle = articleRepository.countArticleFromSchool(profileDomain.getLong("schoolId"));

		List<Domain> dashboardCounters = new ArrayList<>();
		dashboardCounters.add(new Domain().put("title", "total.article").put("icon", "glyphicon glyphicon-stats")
				.put("stats", countArticle));
		dashboardCounters.add(new Domain().put("title", "total.user").put("icon", "glyphicon glyphicon-stats")
				.put("stats", countProfileBySchool));
		dashboardCounters.add(new Domain().put("title", "total.student").put("icon", "glyphicon glyphicon-stats")
				.put("stats", countProfileByStudent));
		dashboardCounters.add(new Domain().put("title", "total.teacher").put("icon", "glyphicon glyphicon-stats")
				.put("stats", countProfileByTeacher));
		Domain counterDomain = new Domain();
		counterDomain.put("counters", dashboardCounters);
		return counterDomain;
	}
}