package com.musictieba.music.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.musictieba.music.dao.impl.musicDaoImpl;
import com.musictieba.music.service.musicService;
import com.musictieba.pojo.Comment;
import com.musictieba.pojo.Music;
import com.musictieba.pojo.Note;
import com.musictieba.pojo.User;
import com.musictieba.util.*;

@Repository("musicService")
public class musicServiceImpl implements musicService{

	@Autowired
	@Qualifier("musicDao")
	private musicDaoImpl musicDao;

	private Map<String, Object> m = new HashMap<String, Object>();
	
	@Override
	public Map<String, Object> addMusic(Music music, String musicUrl, String coverUrl, User u) throws Exception {
		m.clear();
		MultipartFile mp3 = music.getMusicFile();
		MultipartFile cover = music.getCoverFile();
		if (mp3 != null) {
			String mUrl = this.saveMp3(mp3, musicUrl);
			String cUrl = this.saveCover(cover, coverUrl);
			if (mUrl.equals("您上传的不是歌曲！")) {
				m.put("msg", mUrl);
				return m;
			} else {
				music.setMusicUrl(mUrl);
				music.setCoverUrl(cUrl);
				music.setUser(u);
				music.setNote(this.createNote4Music(u, music));
				music.setStatus(true);
				music.setUploadTime(new Timestamp(System.currentTimeMillis()));
				musicDao.add(music);
				m.put("msg", "上传歌曲成功！");
				return m;
			}
		} else {
			m.put("msg", "数据丢失！");
			return m;
		}

	}

	@Override
	public String saveCover(MultipartFile cover, String pathUrl) throws Exception {
		String oldName = cover.getOriginalFilename();
		if (!Util.isEmpty(oldName)) {
			String fileName = Util.now();
			String Suffix = Util.getSuffix(oldName);
			if (Util.isPhoto(Suffix)) {
				return Util.saveFile(pathUrl, fileName, Suffix, cover, "/photo/");
			} else {
				return "没有封面";
			}
		} else {
			return "没有封面";
		}
	}

	@Override
	public String saveMp3(MultipartFile mp3, String pathUrl) throws Exception {
		String fileName = Util.now();
		String oldName = mp3.getOriginalFilename();
		String suffix = Util.getSuffix(oldName);
		if (this.isMusic(suffix)) {
			return Util.saveFile(pathUrl, fileName, suffix, mp3, "/musicfile/");
		} else {
			return "您上传的不是歌曲！";
		}
	}

	@Override
	public boolean isMusic(String Suffix) {
		String tail = Suffix.toLowerCase();
		String[] tails = { ".mp3", ".wav", ".wma" };
		for (int i = 0; i < tails.length; i++) {
			if (tails[i].equals(tail)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Note createNote4Music(User u, Music music) throws Exception {
		Note note4Music = new Note();
		note4Music.setTitle("歌曲《" + music.getMusicName() + "》赏析");
		note4Music.setUser(u);
		note4Music.setContent(this.connect(music));
		note4Music.setCreateTime(new Timestamp(System.currentTimeMillis()));
		note4Music.setLastTime(note4Music.getCreateTime());
		note4Music.setFloors(0);
		note4Music.setStatus(true);
		musicDao.add(note4Music);
		return note4Music;
	}

	@Override
	public String connect(Music m) {
		String connect = "<div>歌曲名：" + m.getMusicName() + "<br>歌手：" + m.getSinger() + "<br>专辑：" + m.getAlbum()
				+ "<br>发行时间:" + m.getYear() + "<br>专辑封面:<img  alt=\"封面\" src=\"http://localhost:8080/MusicTieba/"
				+ m.getCoverUrl() + "\"></div>";
		return connect;

	}

	@Override
	public int countMusic(String searchkey) throws Exception {
		String hql = "";
		List<Object> l = new ArrayList<Object>();
		if (!Util.isEmpty(searchkey)) {
			l.add("%" + searchkey + "%");
			l.add("%" + searchkey + "%");
			l.add("%" + searchkey + "%");
			hql = "SELECT COUNT(*) FROM Music m WHERE m.status = TRUE AND (m.musicName LIKE ? OR m.singer LIKE ? OR m.album LIKE ? )";
			return (int) musicDao.countWithParam(hql, l);
		} else {
			hql = "SELECT COUNT(*) FROM Music m WHERE m.status = TRUE";
			return (int) musicDao.countWithoutparam(hql);
		}
	}

	@Override
	public List<Music> musicList(String searchkey, int nowpageno) throws Exception {
		List<Object> keyList = new ArrayList<>();
		String hql = "";
		if (!Util.isEmpty(searchkey)) {
			keyList.add("%" + searchkey + "%");
			keyList.add("%" + searchkey + "%");
			keyList.add("%" + searchkey + "%");
			hql = "FROM Music m WHERE m.status = TRUE AND (m.musicName LIKE ? OR m.singer LIKE ? OR m.album LIKE ?)";
			return musicDao.queryPageWithParam(hql, nowpageno, 20, keyList);
		} else {
			hql = "FROM Music m WHERE m.status = TRUE";
			return musicDao.queryPageWithoutParam(hql, nowpageno, 20);
		}
	}

	@Override
	public Music findMuisc(Integer muiscId) {
		return (Music) musicDao.findById(Music.class, muiscId);

	}
	
	
	@Override
	public List<Comment> commentList(String noteId, int nowpageno) throws Exception {
		String hql = " from Comment c where c.noteId = ? and c.status = true";
		List<Object> l = new ArrayList<Object>();
		if (noteId != null) {
			l.add(Integer.parseInt(noteId));
			return musicDao.queryPageWithParam(hql, nowpageno, 15, l);
		} else {
			return new ArrayList<Comment>();
		}
	}

	@Override
	public int countComment(String noteid) throws Exception {
		String hql = "";
		List<Object> l = new ArrayList<Object>();
		if (!Util.isEmpty(noteid)) {
			l.add(Integer.parseInt(noteid));
			hql = "select count(*) from Comment c where c.noteId = ? and c.status = true";
			return (int) musicDao.countWithParam(hql, l);
		} else {
			return 0;
		}
	}

	@Override
	public PageBean<Music> qureyPageMusic(String newsk, String topageno) throws Exception {
		PageBean<Music> pb = new PageBean<Music>();
		if (!Util.isEmpty(newsk)) {
			newsk = new String(newsk.getBytes("ISO-8859-1"), "UTF-8");
			pb.setKey(newsk);
		}
		if (!Util.isEmpty(topageno)) {
			pb.setNowPageNo(Integer.parseInt(topageno));
		}
		pb.setCount(countMusic(newsk));
		pb.setSource(musicList(pb.getKey(), pb.getNowPageNo()));
		pb.setPageSize(20);
		pb.setPageLength();
		return pb;
	}
	
	@Override
	public PageBean<Comment> qureyPageComment(String noteId, String topageno) throws Exception {
		PageBean<Comment> pb = new PageBean<Comment>();
		if (!Util.isEmpty(noteId)) {
			pb.setKey(noteId);
		}
		if (!Util.isEmpty(topageno)) {
			pb.setNowPageNo(Integer.parseInt(topageno));
		}
		pb.setCount(countComment(noteId));
		pb.setSource(commentList(pb.getKey(), pb.getNowPageNo()));
		pb.setPageSize(15);
		pb.setPageLength();
		return pb;
	}
	
	@Override
	public PageBean<Music> qureyPageAllMusic(String newsk, String topageno) throws Exception {
		PageBean<Music> pb = new PageBean<Music>();
		if (!Util.isEmpty(newsk)) {
			newsk = new String(newsk.getBytes("ISO-8859-1"), "UTF-8");
			pb.setKey(newsk);
		}
		if (!Util.isEmpty(topageno)) {
			pb.setNowPageNo(Integer.parseInt(topageno));
		}
		pb.setCount(countAllMusic(newsk));
		pb.setSource(musicAllList(pb.getKey(), pb.getNowPageNo()));
		pb.setPageSize(15);
		pb.setPageLength();
		return pb;
	}

	@Override
	public List<Music> musicAllList(String key, int nowPageNo) {
		List<Object> keyList = new ArrayList<>();
		String hql = "";
		if (!Util.isEmpty(key)) {
			keyList.add("%" + key + "%");
			keyList.add("%" + key + "%");
			keyList.add("%" + key + "%");
			hql = "FROM Music m WHERE m.musicName LIKE ? OR m.singer LIKE ? OR m.album LIKE ?";
			return musicDao.queryPageWithParam(hql, nowPageNo, 15, keyList);
		} else {
			hql = "FROM Music";
			return musicDao.queryPageWithoutParam(hql, nowPageNo, 15);
		}
	}

	@Override
	public int countAllMusic(String newsk) {
		String hql = "";
		List<Object> l = new ArrayList<Object>();
		if (!Util.isEmpty(newsk)) {
			l.add("%" + newsk + "%");
			l.add("%" + newsk + "%");
			l.add("%" + newsk + "%");
			hql = "SELECT COUNT(*) FROM Music m WHERE m.musicName LIKE ? OR m.singer LIKE ? OR m.album LIKE ? ";
			return (int) musicDao.countWithParam(hql, l);
		} else {
			hql = "SELECT COUNT(*) FROM Music ";
			return (int) musicDao.countWithoutparam(hql);
		}
	}

	@Override
	public Map<String, Object> changeMusicStatus(String mids) {
		m.clear();
		String[] s = mids.split(",");
		for (String i : s) {
			Integer id = Integer.parseInt(i);
			Music m = (Music) musicDao.findById(Music.class, id);
			m.setStatus(!m.getStatus());
			musicDao.update(m);
		}
		m.put("msg", "操作成功！");
		return m;
	}
}
