package org.slerpio.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.List;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slerpio.entity.Task;

@Entity
@Table(name = "school_class")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class SchoolClass {

	@Id
	@Column(name = "school_class_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHOOL_CLASS_SEQ")
	@SequenceGenerator(name = "SCHOOL_CLASS_SEQ", sequenceName = "school_class_seq", initialValue = 1, allocationSize = 1)
	private Long schoolClassId;
	@Column(name = "name")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.SchoolClass.name")
	private String name;
	@Column(name = "code")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.SchoolClass.code")
	@Size(min = 1, max = 8)
	private String code;
	@Column(name = "user_profile_id")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.SchoolClass.userProfileId")
	private Long userProfileId;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.SchoolClass.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "update_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolClassId")
	private List<Task> taskList;

	@Fetch(FetchMode.SELECT)
	@ManyToMany(mappedBy = "classSet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserProfile> students = new java.util.HashSet<>();

	@JsonProperty
	public Long getSchoolClassId() {
		return schoolClassId;
	}

	public void setSchoolClassId(Long schoolClassId) {
		this.schoolClassId = schoolClassId;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty
	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
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

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public Set<UserProfile> getStudents() {
		return students;
	}

	public void setStudents(Set<UserProfile> students) {
		this.students = students;
	}
	public void addStudent(UserProfile profile) {
		if (!students.contains(profile)) {
			students.add(profile);
			profile.addClass(this);
		}
	}
}