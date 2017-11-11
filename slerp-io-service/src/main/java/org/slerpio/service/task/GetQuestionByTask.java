package org.slerpio.service.task;

import java.util.List;

import org.slerp.core.Domain;
import org.slerp.core.business.DefaultBusinessFunction;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerpio.entity.TaskQuestion;
import org.slerpio.repository.TaskQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@KeyValidation({ "taskId" })
@NumberValidation({})
@NotBlankValidation({})
public class GetQuestionByTask extends DefaultBusinessFunction {

	@Autowired
	TaskQuestionRepository taskQuestionRepository;

	@Override
	public Domain handle(Domain taskQuestionDomain) {
		List<TaskQuestion> taskQuestionPage = taskQuestionRepository
				.getQuestionByTask(taskQuestionDomain.getLong("taskId"));
		return new Domain().put("questions", taskQuestionPage);
	}
}