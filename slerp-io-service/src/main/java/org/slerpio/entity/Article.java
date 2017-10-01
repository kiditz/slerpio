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

@Entity
@Table(name = "article")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class Article {

	@Id
	@Column(name = "article_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_SEQ")
	@SequenceGenerator(name = "ARTICLE_SEQ", sequenceName = "article_seq", initialValue = 1, allocationSize = 1)
	private Long articleId;
	@Column(name = "title")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Article.title")
	@Size(min = 1, max = 60)
	private String title;
	@Column(name = "content")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Article.content")
	private String content;
	@Column(name = "created_at")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Article.createdAt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "last_update")
	@Basic(optional = false)
	@NotNull(message = "org.slerpio.entity.Article.lastUpdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	@ManyToOne
	@JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
	private Profile profileId;
	@ManyToOne
	@JoinColumn(name = "school_id", referencedColumnName = "school_id")
	private School schoolId;

	@JsonProperty
	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	@JsonProperty
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@JsonProperty
	public School getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(School schoolId) {
		this.schoolId = schoolId;
	}
}