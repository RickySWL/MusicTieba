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
@Table(name = "comment")
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3976309731936601342L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", nullable = false)
	private int commentId;

	@Column(name = "n_id", nullable = false)
	private int noteId;

	@Column(name = "content", nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "u_id", nullable = false)
	private User user;

	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Column(name = "status", nullable = false)
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean status;

	public Comment() {

	}

	public Comment(int commentId, int noteId, String content, User user, Date createTime, Boolean status) {
		super();
		this.commentId = commentId;
		this.noteId = noteId;
		this.content = content;
		this.user = user;
		this.createTime = createTime;
		this.status = status;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", noteId=" + noteId + ", content=" + content + ", user=" + user
				+ ", createTime=" + createTime + ", status=" + status + "]";
	}

	
}
