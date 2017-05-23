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
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "music")
public class Music implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4970381023443517706L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "m_id", nullable = false)
	private int musicId;

	@Column(name = "m_name", nullable = false)
	private String musicName;

	@Column(name = "singer", nullable = true)
	private String singer;

	@Column(name = "album", nullable = true)
	private String album;

	@Column(name = "year", nullable = true)
	private String year;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "u_id", nullable = false)
	private User user;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "n_id", nullable = false)
	private Note note;

	@Column(name = "music_url", nullable = false)
	private String musicUrl;

	@Column(name = "cover_url", nullable = true)
	private String coverUrl;

	@Column(name = "upload_time", nullable = false)
	private Date uploadTime;
	
	@Column(name = "status", nullable = false)
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean status;

	@Transient
	private MultipartFile musicFile;
	@Transient
	private MultipartFile coverFile;

	public Music() {

	}

	public Music(int musicId, String musicName, String singer, String album, String year, User user, Note note,
			String musicUrl, String coverUrl, Date uploadTime, MultipartFile musicFile, MultipartFile coverFile) {
		super();
		this.musicId = musicId;
		this.musicName = musicName;
		this.singer = singer;
		this.album = album;
		this.year = year;
		this.user = user;
		this.note = note;
		this.musicUrl = musicUrl;
		this.coverUrl = coverUrl;
		this.uploadTime = uploadTime;
		this.musicFile = musicFile;
		this.coverFile = coverFile;
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public MultipartFile getMusicFile() {
		return musicFile;
	}

	public void setMusicFile(MultipartFile musicFile) {
		this.musicFile = musicFile;
	}

	public MultipartFile getCoverFile() {
		return coverFile;
	}

	public void setCoverFile(MultipartFile coverFile) {
		this.coverFile = coverFile;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Music [musicId=" + musicId + ", musicName=" + musicName + ", singer=" + singer + ", album=" + album
				+ ", year=" + year + ", user=" + user + ", note=" + note + ", musicUrl=" + musicUrl + ", coverUrl="
				+ coverUrl + ", uploadTime=" + uploadTime + ", status=" + status + ", musicFile=" + musicFile
				+ ", coverFile=" + coverFile + "]";
	}
	
	
}
