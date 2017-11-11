package org.slerpio.service.task;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.slerpio.repository.TaskRepository;
import org.slerp.core.Domain;
import org.slerpio.entity.Task;
import org.slerp.core.CoreException;
import org.slerp.core.validation.KeyValidation;
import org.slerp.core.validation.NumberValidation;
import org.slerp.core.business.DefaultBusinessTransaction;

@Service
@Transactional
@KeyValidation("taskId")
@NumberValidation("taskId")
public class RemoveTask extends DefaultBusinessTransaction {

	@Autowired
	TaskRepository taskRepository;
	
	@Override
	public void prepare(Domain taskDomain) throws Exception {
		Task task = taskRepository.findOne(taskDomain.getLong("taskId"));
		if (task == null) {
			throw new CoreException("org.slerpio.entity.Task.notFound");
		}
	}

	@Override
	public Domain handle(Domain taskDomain) {
		super.handle(taskDomain);
		Task task = taskDomain.convertTo(Task.class);		
		taskRepository.delete(task);
		return new Domain().put("success", true);
	}
}