package com.musictieba.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class Note implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1272563289119501219L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "n_id", nullable = false)
	private int noteId;

	@Column(name = "title", nullable = false)
	private String title;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "u_id", nullable = false)
	private User user;

	@Column(name = "content", nullable = false)
	private String content;;

	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Column(name = "last_time", nullable = false)
	private Date lastTime;

	@Column(name = "floors", nullable = false)
	private int floors;
	
	@Column(name = "status", nullable = false)
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean status;


	public Note() {
	}

	public Note(int noteId, String title, User user, String content, Date createTime, Date lastTime, int floors,boolean status) {
		this.noteId = noteId;
		this.title = title;
		this.user = user;
		this.content = content;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.floors = floors;
		this.status = status;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", title=" + title + ", user=" + user + ", content=" + content
				+ ", createTime=" + createTime + ", lastTime=" + lastTime + ", floors=" + floors + ", status=" + status
				+ "]";
	}

	
	
}
