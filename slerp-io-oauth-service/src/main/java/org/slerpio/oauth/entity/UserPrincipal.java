package org.slerpio.oauth.entity;

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
import javax.validation.constraints.Size;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slerpio.oauth.entity.UserAuthority;

/**
 * The entity for userprincipal
 */
@Entity
@Table(name = "user_principal")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@XmlAccessorType(XmlAccessType.NONE)
public class UserPrincipal {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PRINCIPAL_SEQ")
	@SequenceGenerator(name = "USER_PRINCIPAL_SEQ", sequenceName = "user_principal_seq", initialValue = 1, allocationSize = 1)
	private Long userId;
	@Column(name = "username")
	@Basic(optional = false)
	@NotNull(message = "org.slerp.oauth.entity.UserPrincipal.username")
	@Size(min = 1, max = 60)
	private String username;
	@Column(name = "hashed_password")
	@Basic(optional = false)
	@NotNull(message = "org.slerp.oauth.entity.UserPrincipal.hashedPassword")
	private byte[] hashedPassword;

	@Column(name = "email")
	@Basic(optional = false)
	@NotNull(message = "org.slerp.oauth.entity.UserPrincipal.email")
	@Size(min = 1, max = 1000)
	private String email;
	@Column(name = "activation_code")
	@Basic(optional = true)
	// @NotNull(message = "org.slerp.oauth.entity.UserPrincipal.activationCode")
	@Size(min = 1, max = 1000)
	private String activationCode;
	@Column(name = "account_non_expired")
	@Basic(optional = false)
	@NotNull(message = "org.slerp.oauth.entity.UserPrincipal.accountNonExpired")
	private Boolean accountNonExpired;
	@Column(name = "account_non_locked")
	@Basic(optional = false)
	@NotNull(message = "org.slerp.oauth.entity.UserPrincipal.accountNonLocked")
	private Boolean accountNonLocked;
	@Column(name = "credentials_non_expired")
	@Basic(optional = false)
	@NotNull(message = "org.slerp.oauth.entity.UserPrincipal.credentialsNonExpired")
	private Boolean credentialsNonExpired;
	@Column(name = "enabled")
	@Basic(optional = false)
	@NotNull(message = "org.slerp.oauth.entity.UserPrincipal.enabled")
	private Boolean enabled;

	@Column(name = "credentials_non_expired")
	@Fetch(FetchMode.SELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.EAGER)
	private List<UserAuthority> userAuthorityList;

	@JsonProperty
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
	@JsonProperty
	public byte[] getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(byte[] hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	@JsonProperty
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@JsonProperty
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@JsonProperty
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@JsonProperty
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@JsonProperty
	public List<UserAuthority> getUserAuthorityList() {
		return userAuthorityList;
	}

	public void setUserAuthorityList(List<UserAuthority> userAuthorityList) {
		this.userAuthorityList = userAuthorityList;
	}

}