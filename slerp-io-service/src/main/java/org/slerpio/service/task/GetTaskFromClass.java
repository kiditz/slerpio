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
import org.springframework.data.domain.Sort.Direction;

@Service
@KeyValidation({ "classId", "page", "size" })
@NumberValidation({})
@NotBlankValidation({})
public class GetTaskFromClass extends DefaultBusinessFunction {

	@Autowired
	TaskRepository taskRepository;

	@Override
	public Domain handle(Domain taskDomain) {
		int page = taskDomain.getInt("page");
		int size = taskDomain.getInt("size");
		Page<Task> taskPage = taskRepository.getTaskFromClass(taskDomain.getLong("classId"),
				new PageRequest(page, size, Direction.DESC, "taskId"));
		return new Domain().put("taskPage", taskPage);
	}
}