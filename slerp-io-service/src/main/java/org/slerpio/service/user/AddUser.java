package org.slerpio.service.user;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.entity.School;
import org.slerpio.entity.Student;
import org.slerpio.entity.Teacher;
import org.slerpio.repository.SchoolRepository;
import org.slerpio.repository.StudentRepository;
import org.slerpio.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "firstname", "lastname", "username", "schoolId" })
@NotBlankValidation("type")
public class AddUser extends DefaultBusinessTransaction {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	SchoolRepository schoolRepository;
	@Autowired
	TeacherRepository teacherRepository;

	@Override
	public void prepare(Domain studentDomain) throws Exception {

		Domain schoolDomain = studentDomain.getDomain("schoolId");
		School schoolId = schoolDomain.convertTo(School.class);
		schoolId = schoolRepository.findOne(schoolId.getSchoolId());
		if (schoolId == null) {
			throw new CoreException("failed.to.found.school");
		}

		studentDomain.put("schoolId", new Domain(schoolId));
	}

	@Override
	public Domain handle(Domain studentDomain) {
		super.handle(studentDomain);
		try {
			String username = studentDomain.getString("username");
			if (studentDomain.getString("type").equals("student")) {

				if (studentRepository.isExistByUsername(username)) {
					throw new CoreException("data.has.been.exist");
				}
				Student student = studentDomain.convertTo(Student.class);
				student = studentRepository.save(student);
				return new Domain(student);
			} else if (studentDomain.getString("type").equals("teacher")) {
				if (teacherRepository.isExistByUsername(username)) {
					throw new CoreException("data.has.been.exist");
				}
				Teacher teacher = studentDomain.convertTo(Teacher.class);
				teacher = teacherRepository.save(teacher);
				return new Domain(teacher);
			} else {
				throw new CoreException("unknown.type");
			}
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}