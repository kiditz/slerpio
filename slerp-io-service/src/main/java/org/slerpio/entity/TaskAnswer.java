package org.slerpio.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
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
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "task_answer")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class TaskAnswer {

	@Id
	@Column(name = "task_answer_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_ANSWER_SEQ")
	@SequenceGenerator(name = "TASK_ANSWER_SEQ", sequenceName = "task_answer_seq", initialValue = 1, allocationSize = 1)
	private Long taskAnswerId;
	@Column(name = "answer")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.TaskAnswer.answer")
	private String answer;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.TaskAnswer.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "update_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.TaskAnswer.updateAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_question_id", referencedColumnName = "task_question_id")
	private TaskQuestion taskQuestionId;

	@JsonProperty
	public Long getTaskAnswerId() {
		return taskAnswerId;
	}

	public void setTaskAnswerId(Long taskAnswerId) {
		this.taskAnswerId = taskAnswerId;
	}

	@JsonProperty
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@JsonProperty
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty
	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	// @JsonProperty
	public TaskQuestion getTaskQuestionId() {
		return taskQuestionId;
	}

	public void setTaskQuestionId(TaskQuestion taskQuestionId) {
		this.taskQuestionId = taskQuestionId;
	}
}