package org.slerpio.repository;

import org.slerpio.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT t FROM Task t WHERE t.schoolClassId.schoolClassId = :schoolClassId")
	public Page<Task> getTaskBySchoolClassId(@Param("schoolClassId") Long schoolClassId, Pageable pageable);
}