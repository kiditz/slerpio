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
import java.util.List;

@Service
@KeyValidation("schoolClassId")
@NumberValidation({})
@NotBlankValidation({})
public class GetStudentByClassId extends DefaultBusinessFunction {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Domain handle(Domain studentDomain) {
		List<Student> studentList = studentRepository.getStudentByClassId(studentDomain.getLong("schoolClassId"));
		return new Domain().put("studentList", studentList);
	}
}