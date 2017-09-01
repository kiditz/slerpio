package org.slerpio.service.task;

import org.slerp.core.business.DefaultBusinessFunction;
import org.slerpio.entity.Task;
import org.springframework.stereotype.Service;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.validation.NotBlankValidation;
import org.slerp.core.Domain;
import org.slerpio.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
@KeyValidation({"schoolClassId", "page", "size"})
@NumberValidation({})
@NotBlankValidation({})
public class GetTaskBySchoolClassId extends DefaultBusinessFunction {

	@Autowired
	TaskRepository taskRepository;

	@Override
	public Domain handle(Domain taskDomain) {
		int page = taskDomain.getInt("page");
		int size = taskDomain.getInt("size");
		Page<Task> taskPage = taskRepository.getTaskBySchoolClassId(
				taskDomain.getLong("schoolClassId"),
				new PageRequest(page, size));
		return new Domain().put("taskPage", taskPage);
	}
}