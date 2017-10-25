package org.slerpio.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "user_profile")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class UserProfile implements Serializable {

	@Id
	@Column(name = "profile_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PROFILE_SEQ")
	@SequenceGenerator(name = "USER_PROFILE_SEQ", sequenceName = "user_profile_seq", initialValue = 1, allocationSize = 1)
	private Long profileId;
	@Column(name = "phone_number")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.UserProfile.userId")
	private String phoneNumber;
	@Column(name = "fullname")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.UserProfile.fullname")
	private String fullname;
	@Column(name = "gender")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.UserProfile.gender")
	@Size(min = 1, max = 1)
	private String gender;
	@Column(name = "address")
	@Size(min = 1, max = 20)
	private String address;
	@Column(name = "latitude")
	private BigDecimal latitude;
	@Column(name = "longitude")
	private BigDecimal longitude;
	@Column(name = "active")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.UserProfile.active")
	@Size(min = 1, max = 1)
	private String active;
	@Column(name = "active_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.UserProfile.activeAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date activeAt;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.UserProfile.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "update_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;
	@Fetch(FetchMode.SELECT)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "profile_has_school", joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "profile_id"), inverseJoinColumns = @JoinColumn(name = "school_id", referencedColumnName = "school_id"))
	private Set<School> schoolSet = new HashSet<>();

	@JsonProperty
	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@JsonProperty
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@JsonProperty
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	@JsonProperty
	public Set<School> getSchoolSet() {
		return schoolSet;
	}

	public void setSchoolSet(Set<School> schoolSet) {
		this.schoolSet = schoolSet;
	}

	public void addSchool(School school) {
		if (!schoolSet.contains(school)) {
			schoolSet.add(school);
			school.addUserProfile(this);
		}
	}
}