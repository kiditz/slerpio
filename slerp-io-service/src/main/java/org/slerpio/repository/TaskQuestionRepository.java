package org.slerpio.repository;

import java.util.List;

import org.slerpio.entity.TaskQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskQuestionRepository extends JpaRepository<TaskQuestion, Long> {

	@Query("SELECT q FROM TaskQuestion q JOIN q.taskId t WHERE t.taskId = :taskId")
	public List<TaskQuestion> getQuestionByTask(@Param("taskId") Long taskId);
}