package org.slerpio.service.teacher;

import org.slerp.core.business.DefaultBusinessFunction;
import org.slerpio.entity.Teacher;
import org.springframework.stereotype.Service;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.Domain;
import org.slerpio.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@KeyValidation("username")
@NumberValidation({})
@NotBlankValidation({ "username" })
public class GetTeacherByUsername extends DefaultBusinessFunction {

	@Autowired
	TeacherRepository teacherRepository;

	@Override
	public Domain handle(Domain teacherDomain) {
		Teacher teacher = teacherRepository.getTeacherByUsername(teacherDomain.getString("username"));
		return new Domain().put("teacher", teacher);
	}
}