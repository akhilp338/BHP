package com.belhopat.backoffice.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "USERNAME", nullable = false, unique = true, length = 50)
	private String username;

	@JsonIgnore
	@Column(name = "PASSWORD", nullable = false, length = 100)
	private String password;

	@Column(name = "EMAIL", nullable = false, unique = true, length = 50)
	private String email;

	@ManyToOne
	@JoinColumn(name = "DSGNTN_ID", nullable = false)
	private LookupDetail designation;

	@Column(name = "EMP_ID", nullable = false)
	private Long employeeId;

	@Lob
	@Column(name = "USR_IMG", nullable = true)
	byte[] userImage;

	@JsonIgnore
	@Column(name = "SALT")
	private Byte[] salt;

	@JsonIgnore
	@Column(name = "FRGT_PSWD_STS", columnDefinition = "boolean default false", nullable = false)
	private Boolean forgotPasswordStatus;

	@JsonIgnore
	@Column(name = "FRGT_PSWD_TKN", length = 200)
	private String forgotPasswordToken;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MST_ROLE")
	private MasterRole primaryRole;

	public MasterRole getPrimaryRole() {
		return primaryRole;
	}

	public void setPrimaryRole(MasterRole primaryRole) {
		this.primaryRole = primaryRole;
	}

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<MasterRole> roles = new HashSet<MasterRole>();

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	public User(Long id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LookupDetail getDesignation() {
		return designation;
	}

	public void setDesignation(LookupDetail designation) {
		this.designation = designation;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}

	public Byte[] getSalt() {
		return salt;
	}

	public void setSalt(Byte[] salt) {
		this.salt = salt;
	}

	public Boolean getForgotPasswordStatus() {
		return forgotPasswordStatus;
	}

	public void setForgotPasswordStatus(Boolean forgotPasswordStatus) {
		this.forgotPasswordStatus = forgotPasswordStatus;
	}

	public String getForgotPasswordToken() {
		return forgotPasswordToken;
	}

	public void setForgotPasswordToken(String forgotPasswordToken) {
		this.forgotPasswordToken = forgotPasswordToken;
	}

	public Set<MasterRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<MasterRole> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", designation=" + designation + ", salt=" + Arrays.toString(salt) + ", forgotPasswordStatus="
				+ forgotPasswordStatus + ", forgotPasswordToken=" + forgotPasswordToken + ", primaryRole=" + primaryRole
				+ ", roles=" + roles + "]";
	}

}
