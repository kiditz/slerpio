package org.slerpio.repository;

import org.slerpio.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT t FROM Task t JOIN t.studentFinishingTaskList s WHERE s.userProfileId.profileId = :userProfileId AND s.flgFinish = 'N'")
	public Page<Task> getTaskFromProfile(
			@Param("userProfileId") Long profileId, Pageable pageable);

	@Query("SELECT t FROM Task t WHERE t.schoolId.schoolId = :schoolId")
	public Page<Task> getTaskFromSchool(@Param("schoolId") long schoolId,
			Pageable pageable);

	@Query("SELECT t FROM Task t WHERE t.userProfileId.profileId = :profileId")
	public Page<Task> getTaskByTeacher(@Param("profileId") Long profileId,
			Pageable pageable);

	@Query("SELECT t FROM Task t WHERE t.schoolClassId.schoolClassId = :classId")
	public Page<Task> getTaskFromClass(@Param("classId") long classId,
			Pageable pageable);
}