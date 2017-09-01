package org.slerpio.service.student;

import org.slerp.core.business.DefaultBusinessFunction;
import org.slerpio.entity.Student;
import org.springframework.stereotype.Service;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.Domain;
import org.slerpio.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@KeyValidation("username")
@NumberValidation({})
@NotBlankValidation({})
public class GetStudentByUsername extends DefaultBusinessFunction {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Domain handle(Domain studentDomain) {
		Student student = studentRepository.getStudentByUsername(studentDomain.getString("username"));
		return new Domain().put("student", student);
	}
}