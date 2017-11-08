package org.slerpio.service.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessTransaction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerpio.entity.School;
import org.slerpio.entity.SchoolClass;
import org.slerpio.entity.StudentFinishingTask;
import org.slerpio.entity.Task;
import org.slerpio.entity.TaskAnswer;
import org.slerpio.entity.TaskQuestion;
import org.slerpio.entity.UserProfile;
import org.slerpio.repository.SchoolClassRepository;
import org.slerpio.repository.SchoolRepository;
import org.slerpio.repository.TaskRepository;
import org.slerpio.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@KeyValidation({ "title", "description", "schoolClassId", "userProfileId", "schoolId" })
@NotBlankValidation({ "title" })
public class AddTask extends DefaultBusinessTransaction {

	@Autowired
	TaskRepository taskRepository;
	@Autowired
	SchoolClassRepository schoolClassRepository;
	@Autowired
	UserProfileRepository userProfileRepository;
	@Autowired
	SchoolRepository schoolRepository;
	private Task task;
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void prepare(Domain taskDomain) throws Exception {

	}

	@Override
	public Domain handle(Domain taskDomain) {
		super.handle(taskDomain);
		try {
			Long schoolClassId = taskDomain.getLong("schoolClassId");
			SchoolClass schoolClass = schoolClassRepository.findOne(schoolClassId);
			if (schoolClassId == null) {
				throw new CoreException("schoolClass.notFound");
			}
			Long userProfileId = taskDomain.getLong("userProfileId");
			UserProfile userProfile = userProfileRepository.findOne(userProfileId);
			if (userProfileId == null) {
				throw new CoreException("userProfile.notFound");
			}
			Long schoolId = taskDomain.getLong("schoolId");
			School school = schoolRepository.findOne(schoolId);
			if (schoolId == null) {
				throw new CoreException("school.notFound");
			}
			task = new Task();
			task.setTitle(taskDomain.getString("title"));
			task.setDescription(taskDomain.getString("description"));
			task.setSchoolId(school);
			task.setUserProfileId(userProfile);
			task.setSchoolClassId(schoolClass);
			task.setCreatedAt(new Date());
			task.setUpdateAt(new Date());
			List<Domain> questionListDomain = taskDomain.getList("questions");
			@SuppressWarnings("unchecked")
			List<TaskQuestion> questions = questionListDomain.stream().map(domain -> {
				log.info("Question >>> {}", domain);
				TaskQuestion question = new TaskQuestion();

				question.setQuestion(domain.getString("question"));
				question.setAnsweredKey(domain.getString("answeredKey"));
				question.setTaskId(task);
				question.setCreatedAt(new Date());
				question.setUpdateAt(new Date());

				@SuppressWarnings("rawtypes")
				List<Object> answersArr = (ArrayList) domain.get("answers");
				List<TaskAnswer> answers = answersArr.stream().map(answerStr -> {
					TaskAnswer answer = new TaskAnswer();
					answer.setAnswer(answerStr.toString());
					answer.setTaskQuestionId(question);
					answer.setCreatedAt(new Date());
					answer.setUpdateAt(new Date());
					return answer;
				}).collect(Collectors.toList());
				question.setTaskAnswerList(answers);
				return question;
			}).collect(Collectors.toList());

			List<StudentFinishingTask> studentTasks = schoolClassRepository.getStudentFromClassId(schoolClassId)
					.stream().map(p -> {
						StudentFinishingTask studentFinishingTask = new StudentFinishingTask();
						studentFinishingTask.setFlgFinish("N");
						studentFinishingTask.setTaskId(task);
						studentFinishingTask.setCreatedAt(new Date());
						studentFinishingTask.setUpdateAt(new Date());
						studentFinishingTask.setUserProfileId(p);
						return studentFinishingTask;
					}).collect(Collectors.toList());
			task.setTaskQuestionList(questions);
			task.setStudentFinishingTaskList(studentTasks);
			task = taskRepository.save(task);

			return new Domain(task);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CoreException(e);
		}
	}
}