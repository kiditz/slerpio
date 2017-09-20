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
import javax.validation.constraints.Size;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

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
	@Column(name = "class_title")
	@Size(min = 1, max = 60)
	private String classTitle;
	@Column(name = "class_code")
	@Size(min = 1, max = 60)
	private String classCode;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.SchoolClass.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "last_update")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.SchoolClass.lastUpdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	@ManyToOne
	@JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
	private Profile profileId;

	@JsonProperty
	public Long getSchoolClassId() {
		return schoolClassId;
	}

	public void setSchoolClassId(Long schoolClassId) {
		this.schoolClassId = schoolClassId;
	}

	@JsonProperty
	public String getClassTitle() {
		return classTitle;
	}

	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}

	@JsonProperty
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	@JsonProperty
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@JsonProperty
	public Profile getProfileId() {
		return profileId;
	}

	public void setProfileId(Profile profileId) {
		this.profileId = profileId;
	}
}