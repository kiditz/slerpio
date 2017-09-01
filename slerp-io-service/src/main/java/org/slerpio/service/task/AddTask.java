package org.slerpio.service.task;

import java.util.ArrayList;
import java.util.List;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.entity.SchoolClass;
import org.slerpio.entity.Student;
import org.slerpio.entity.Task;
import org.slerpio.entity.TaskAnswer;
import org.slerpio.entity.TaskQuestion;
import org.slerpio.entity.TaskUser;
import org.slerpio.repository.StudentRepository;
import org.slerpio.repository.TaskQuestionRepository;
import org.slerpio.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "title", "timer", "description", "taskDate", "lastUpdate", "schoolClassId.schoolClassId",
		"taskQuestionList" })
@NotBlankValidation({ "title", "description" })
public class AddTask extends DefaultBusinessTransaction {
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	TaskQuestionRepository taskQuestionRepository;
	@Autowired
	StudentRepository studentRepository;

	@Override
	public void prepare(Domain taskDomain) throws Exception {
				
	}

	@Override
	public Domain handle(Domain taskDomain) {
		super.handle(taskDomain);
		try {
			Task task = taskDomain.convertTo(Task.class);
			SchoolClass schoolClass = taskDomain.getDomain("schoolClassId").convertTo(SchoolClass.class);
			task.setSchoolClassId(schoolClass);
			List<TaskQuestion> questions = new ArrayList<>();
			for (Domain taskQuestion : taskDomain.getList("taskQuestionList")) {
				TaskQuestion question = taskQuestion.convertTo(TaskQuestion.class);
				question.setTaskId(task);
				List<TaskAnswer> taskAnswerList = new ArrayList<>();
				for (Domain taskAnswer : taskQuestion.getList("taskAnswerList")) {
					TaskAnswer answer = taskAnswer.convertTo(TaskAnswer.class);
					answer.setTaskQuestionId(question);
					taskAnswerList.add(answer);
				}
				question.setTaskAnswerList(taskAnswerList);
				questions.add(question);
			}
			task.setTaskQuestionList(questions);
			List<Student> studentList = studentRepository
					.getStudentByClassId(taskDomain.getDomain("schoolClassId").getLong("schoolClassId"));
			List<TaskUser> users = new ArrayList<>();
			for (Student student : studentList) {
				TaskUser user = new TaskUser();
				user.setIsTaskFinished(false);
				user.setStudentId(student);
				user.setTaskId(task);
				user.setVersion(0l);
				users.add(user);
			}
			task.setTaskUserList(users);
			Task resultTask = taskRepository.save(task);
			return new Domain(resultTask);
		} catch (Exception e) {
			throw new CoreException(e);
		}
	}
}