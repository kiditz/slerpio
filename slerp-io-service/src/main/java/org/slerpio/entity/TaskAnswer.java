package org.slerpio.entity;

import javax.persistence.Entity;
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
	@Column(name = "text")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.TaskAnswer.text")
	private String text;
	@ManyToOne(optional = false)
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
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty
	public TaskQuestion getTaskQuestionId() {
		return taskQuestionId;
	}

	public void setTaskQuestionId(TaskQuestion taskQuestionId) {
		this.taskQuestionId = taskQuestionId;
	}
}