package com.musictieba.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1835671851464068061L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "u_id", nullable = false)
	private int userId;

	@Column(name = "account", nullable = false)
	private String account;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "admin", nullable = false)
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean admin;

	@Column(name = "u_name", nullable = false)
	private String userName;

	@Column(name = "photo_url", nullable = true)
	private String photoUrl;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Column(name = "status", nullable = false)
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean status;

	@Transient
	private MultipartFile photoFile;

	public User() {

	}

	public User(int userId, String account, String password, Boolean admin, String userName,
			String photoUrl, String email, Date createTime, MultipartFile photoFile) {
		super();
		this.userId = userId;
		this.account = account;
		this.password = password;
		this.admin = admin;
		this.userName = userName;
		this.photoUrl = photoUrl;
		this.email = email;
		this.createTime = createTime;
		this.photoFile = photoFile;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public MultipartFile getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(MultipartFile photoFile) {
		this.photoFile = photoFile;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", account=" + account + ", password=" + password
				+ ", admin=" + admin + ", userName=" + userName + ", photoUrl=" + photoUrl + ", email="
				+ email + ", createTime=" + createTime + ", status=" + status + ", photoFile=" + photoFile + "]";
	}
	
	
	
}