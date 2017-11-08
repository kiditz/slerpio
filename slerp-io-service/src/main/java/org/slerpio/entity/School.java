package org.slerpio.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "school")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class School  {

	@Id
	@Column(name = "school_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHOOL_SEQ")
	@SequenceGenerator(name = "SCHOOL_SEQ", sequenceName = "school_seq", initialValue = 1, allocationSize = 1)
	private Long schoolId;
	@Column(name = "name")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.School.name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "address")
	@Size(min = 1, max = 20)
	private String address;
	@Column(name = "latitude")
	private BigDecimal latitude;
	@Column(name = "longitude")
	private BigDecimal longitude;
	@Column(name = "active")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.School.active")
	@Size(min = 1, max = 1)
	private String active;
	@Column(name = "active_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.School.activeAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date activeAt;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.School.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "update_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;
	@Fetch(FetchMode.SELECT)
	@ManyToMany(mappedBy = "schoolSet", cascade = CascadeType.ALL)
	private Set<UserProfile> userProfiles = new java.util.HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolId")
	private List<Task> taskList;

	@JsonProperty
	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty
	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@JsonProperty
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	@JsonProperty
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@JsonProperty
	public Date getActiveAt() {
		return activeAt;
	}

	public void setActiveAt(Date activeAt) {
		this.activeAt = activeAt;
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

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public void addUserProfile(UserProfile profile) {
		if (!userProfiles.contains(profile)) {
			userProfiles.add(profile);
			profile.addSchool(this);
		}
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
}