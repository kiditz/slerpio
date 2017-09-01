package org.slerpio.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slerpio.entity.TaskUser;

@Entity
@Table(name = "task")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class Task {

	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_ANSWER_SEQ")
	@SequenceGenerator(name = "TASK_ANSWER_SEQ", sequenceName = "task_answer_seq", initialValue = 1, allocationSize = 1)
	private Long taskId;
	@Column(name = "title")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Task.title")
	@Size(min = 1, max = 60)
	private String title;
	@Column(name = "description")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Task.description")
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
	@Column(name = "task_date")
	@Temporal(TemporalType.DATE)
	private Date taskDate;
	@Column(name = "last_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	@Column(name = "timer")
	private String timer;
	@ManyToOne(optional = false)
	@JoinColumn(name = "school_class_id", referencedColumnName = "school_class_id")
	private SchoolClass schoolClassId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskId")
	private List<TaskQuestion> taskQuestionList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskId")
	private List<TaskUser> taskUserList;

	@JsonProperty
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@JsonProperty
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty
	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	@JsonProperty
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@JsonProperty
	public String getTimer() {
		return timer;
	}

	public void setTimer(String timer) {
		this.timer = timer;
	}

	// @JsonProperty
	public SchoolClass getSchoolClassId() {
		return schoolClassId;
	}

	public void setSchoolClassId(SchoolClass schoolClassId) {
		this.schoolClassId = schoolClassId;
	}

	public List<TaskQuestion> getTaskQuestionList() {
		return taskQuestionList;
	}

	public void setTaskQuestionList(List<TaskQuestion> taskQuestionList) {
		this.taskQuestionList = taskQuestionList;
	}

	public List<TaskUser> getTaskUserList() {
		return taskUserList;
	}

	public void setTaskUserList(List<TaskUser> taskUserList) {
		this.taskUserList = taskUserList;
	}
}