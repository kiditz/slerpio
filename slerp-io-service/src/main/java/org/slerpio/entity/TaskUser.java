package org.slerpio.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "task_user")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class TaskUser {

	@Id
	@Column(name = "task_user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_USER_SEQ")
	@SequenceGenerator(name = "TASK_USER_SEQ", sequenceName = "task_user_seq", initialValue = 1, allocationSize = 1)
	private Long taskUserId;
	@Column(name = "is_task_finished")
	private Boolean isTaskFinished;
	
	@Version
	@Column(name = "version")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.TaskUser.version")
	private Long version;

	@ManyToOne(optional = false)
	@JoinColumn(name = "student_id", referencedColumnName = "student_id")
	private Student studentId;
	@ManyToOne(optional = false)
	@JoinColumn(name = "task_id", referencedColumnName = "task_id")
	private Task taskId;

	@JsonProperty
	public Long getTaskUserId() {
		return taskUserId;
	}

	public void setTaskUserId(Long taskUserId) {
		this.taskUserId = taskUserId;
	}

	@JsonProperty
	public Boolean getIsTaskFinished() {
		return isTaskFinished;
	}

	public void setIsTaskFinished(Boolean isTaskFinished) {
		this.isTaskFinished = isTaskFinished;
	}

	@JsonProperty
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@JsonProperty
	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}

	@JsonProperty
	public Task getTaskId() {
		return taskId;
	}

	public void setTaskId(Task taskId) {
		this.taskId = taskId;
	}
}