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
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import org.slerpio.entity.Article;
import org.slerpio.entity.Contact;

@Entity
@Table(name = "profile")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class Profile {

	@Id
	@Column(name = "profile_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_SEQ")
	@SequenceGenerator(name = "PROFILE_SEQ", sequenceName = "profile_seq", initialValue = 1, allocationSize = 1)
	private Long profileId;
	@Column(name = "username", length = 60)
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Profile.username")
	@Size(min = 1, max = 60)
	private String username;
	@Column(name = "email", length = 60)
	// @Basic(optional = false)
	// @NotNull(message = "org.slerpio.entity.Profile.email")
	// @Size(min = 1, max = 60)
	private String email;
	@Column(name = "phone_number", length = 15)
	//@Basic(optional = false)
	//@NotNull(message = "org.slerpio.entity.Profile.phoneNumber")
	//@Size(min = 1, max = 15)
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
	@Column(name = "authority")
	@Size(min = 1, max = 20)
	private String authority;

	@ManyToOne
	@JoinColumn(name = "school_id", referencedColumnName = "school_id")
	private School schoolId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profileId")
	private List<Contact> contactList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profileId")
	private List<Article> articleList;

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
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@JsonProperty
	public School getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(School schoolId) {
		this.schoolId = schoolId;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}
}