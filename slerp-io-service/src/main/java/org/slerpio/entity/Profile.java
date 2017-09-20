package org.slerpio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.slerpio.entity.SchoolClass;

@Entity
@Table(name = "profile")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "profile_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_SEQ")
	@SequenceGenerator(name = "PROFILE_SEQ", sequenceName = "profile_seq", initialValue = 1, allocationSize = 1)
	private Long profileId;
	@Column(name = "username")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Profile.username")
	private String username;
	@Column(name = "email")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Profile.email")
	private String email;
	@Column(name = "phone_number")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Profile.phoneNumber")
	private String phoneNumber;
	@Column(name = "fullname")
	private String fullname;
	@Column(name = "image_path")
	private String imagePath;
	@Column(name = "address")
	private String address;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Profile.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "last_update")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Profile.lastUpdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	@ManyToOne
	@JoinColumn(name = "school_id", referencedColumnName = "school_id")
	private School schoolId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profileId")
	private List<SchoolClass> schoolClassList;

	@JsonProperty
	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@JsonProperty
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@JsonProperty
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	public School getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(School schoolId) {
		this.schoolId = schoolId;
	}

	public List<SchoolClass> getSchoolClassList() {
		return schoolClassList;
	}

	public void setSchoolClassList(List<SchoolClass> schoolClassList) {
		this.schoolClassList = schoolClassList;
	}
}