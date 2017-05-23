package com.musictieba.note.service.impl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.musictieba.note.dao.impl.NoteDaoimlp;
import com.musictieba.note.service.NoteSetvice;
import com.musictieba.pojo.Comment;
import com.musictieba.pojo.Note;
import com.musictieba.pojo.User;
import com.musictieba.util.PageBean;
import com.musictieba.util.Util;

@Repository("noteService")
public class NoteServiceImlp implements NoteSetvice {

	@Autowired
	@Qualifier("noteDao")
	private NoteDaoimlp nDao;

	private Map<String, Object> m = new HashMap<String, Object>();

	@Override
	public void addNote(Note note) throws Exception {
		note.setCreateTime(new Timestamp(System.currentTimeMillis()));
		note.setLastTime(note.getCreateTime());
		note.setFloors(0);
		note.setStatus(true);
		nDao.add(note);
	}

	@Override
	public int countNote(String sk) throws Exception {
		String hql = "";
		List<Object> l = new ArrayList<Object>();
		if (!Util.isEmpty(sk)) {
			l.add("%" + sk + "%");
			hql = "select count(*) from Note n where n.title like ? and n.status = true";
			return (int) nDao.countWithParam(hql, l);
		} else {
			hql = "select count(*) from Note n where n.status = true";
			return (int) nDao.countWithoutparam(hql);
		}
	}

	@Override
	public int countAllNote(String sk) throws Exception {
		String hql = "";
		List<Object> l = new ArrayList<Object>();
		if (!Util.isEmpty(sk)) {
			l.add("%" + sk + "%");
			hql = "select count(*) from Note n where n.title like ? ";
			return (int) nDao.countWithParam(hql, l);
		} else {
			hql = "select count(*) from Note ";
			return (int) nDao.countWithoutparam(hql);
		}
	}

	@Override
	public List<Note> noteList(String sk, int nowpageno) throws Exception {
		String hql = "";
		List<Object> keyList = new ArrayList<>();
		if (!Util.isEmpty(sk)) {
			keyList.add("%" + sk + "%");
			hql = "from Note n where n.title like ? and n.status = true";
			return nDao.queryPageWithParam(hql, nowpageno, 20, keyList);
		} else {
			hql = "from Note n where n.status = true";
			return nDao.queryPageWithoutParam(hql, nowpageno, 20);
		}
	}

	@Override
	public List<Note> noteAllList(String sk, int nowpageno) throws Exception {
		String hql = "";
		List<Object> keyList = new ArrayList<>();
		if (!Util.isEmpty(sk)) {
			keyList.add("%" + sk + "%");
			hql = "from Note n where n.title like ? ";
			return nDao.queryPageWithParam(hql, nowpageno, 20, keyList);
		} else {
			hql = "from Note";
			return nDao.queryPageWithoutParam(hql, nowpageno, 20);
		}
	}

	@Override
	public Map<String, Object> queryNote(String noteid) throws Exception {
		int id = Integer.parseInt(noteid);
		Note note4read = (Note) nDao.findById(Note.class, id);
		if (note4read != null) {
			m.put("note", note4read);
			return m;
		} else {
			m.put("msg", "数据丢失！");
			return m;
		}

	}

	@Override
	public int countComment(String noteid) throws Exception {
		String hql = "";
		List<Object> l = new ArrayList<Object>();
		if (!Util.isEmpty(noteid)) {
			l.add(Integer.parseInt(noteid));
			hql = "select count(*) from Comment c where c.noteId = ? and c.status = true";
			return (int) nDao.countWithParam(hql, l);
		} else {
			return 0;
		}
	}

	@Override
	public void addComment(Comment c, User u) throws Exception {
		c.setStatus(true);
		c.setCreateTime(new Timestamp(System.currentTimeMillis()));
		c.setUser(u);
		Note note4update = (Note) nDao.findById(Note.class, c.getNoteId());
		if (note4update != null) {
			note4update.setLastTime(c.getCreateTime());
			note4update.setFloors(note4update.getFloors() + 1);
		}
		nDao.merge(c);
		nDao.merge(note4update);
	}

	@Override
	public List<Comment> commentList(String noteId, int nowpageno) throws Exception {
		String hql = " from Comment c where c.noteId = ? and c.status = true";
		List<Object> l = new ArrayList<Object>();
		if (noteId != null) {
			l.add(Integer.parseInt(noteId));
			return nDao.queryPageWithParam(hql, nowpageno, 15, l);
		} else {
			return new ArrayList<Comment>();
		}
	}

	@Override
	public Map<String, Object> changeCommentStatus(Integer cid) throws Exception {
		if (cid != null) {
			Comment comment4del = (Comment) nDao.findById(Comment.class, cid);
			comment4del.setStatus(false);
			nDao.update(comment4del);
			m.put("msg", "删除回复成功！");
			return m;
		} else {
			m.put("msg", "数据丢失！");
			return m;
		}

	}

	public Map<String, Object> changeNoteStatus(int noteid) throws Exception {
		if (noteid != 0) {
			Note note4del = (Note) nDao.findById(Note.class, noteid);
			note4del.setStatus(false);
			nDao.update(note4del);
			m.put("msg", "删除帖子成功！");
			return m;
		} else {
			m.put("msg", "数据丢失！");
			return m;
		}
	}

	@Override
	public List<Note> myNoteList(String searchkey, int nowpageno, User u) throws Exception {
		String hql = "";
		List<Object> keyList = new ArrayList<Object>();

		if (!Util.isEmpty(searchkey)) {
			keyList.add("%" + searchkey + "%");
			keyList.add(u);
			hql = " FROM Note n WHERE n.status = true AND n.title like ? AND n.user = ?";
			return nDao.queryPageWithParam(hql, nowpageno, 15, keyList);
		} else {
			keyList.add(u);
			hql = " FROM Note n WHERE n.status = true AND  n.user = ?";
			return nDao.queryPageWithParam(hql, nowpageno, 15, keyList);
		}
	}

	public int countMyNote(String searchkey, User u) throws Exception {
		String hql = "";
		List<Object> keyList = new ArrayList<Object>();
		if (!Util.isEmpty(searchkey)) {
			keyList.add("%" + searchkey + "%");
			keyList.add(u);
			hql = "SELECT COUNT(*) FROM Note n WHERE n.status = true AND n.title like ? AND n.user = ?";
			return (int) nDao.countWithParam(hql, keyList);
		} else {
			keyList.add(u);
			hql = "SELECT COUNT(*) FROM Note n WHERE n.status = true AND  n.user = ?";
			return (int) nDao.countWithParam(hql, keyList);
		}
	}

	public PageBean<Note> qureyPageNote(String searchkey, String topageno) throws Exception {
		PageBean<Note> pb = new PageBean<Note>();
		if (!Util.isEmpty(searchkey)) {
			searchkey = new String(searchkey.getBytes("ISO-8859-1"), "UTF-8");
			pb.setKey(searchkey);
		}
		if (!Util.isEmpty(topageno)) {
			pb.setNowPageNo(Integer.parseInt(topageno));
		}
		pb.setCount(countNote(searchkey));
		pb.setSource(noteList(pb.getKey(), pb.getNowPageNo()));
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
		pb.setPageLength();
		return pb;
	}

	public PageBean<Note> qureyPageNoteByUser(String searchkey, String topageno, User currentUser) throws Exception {
		PageBean<Note> pb = new PageBean<Note>();
		if (!Util.isEmpty(searchkey)) {
			searchkey = new String(searchkey.getBytes("ISO-8859-1"), "UTF-8");
			pb.setKey(searchkey);
		}
		if (!Util.isEmpty(topageno)) {
			pb.setNowPageNo(Integer.parseInt(topageno));
		}
		pb.setCount(countMyNote(searchkey, currentUser));
		pb.setSource(myNoteList(pb.getKey(), pb.getNowPageNo(), currentUser));
		pb.setPageSize(15);
		pb.setPageLength();
		return pb;
	}

	@Override
	public PageBean<Note> qureyPageAllNotes(String newsk, String topageno) throws Exception {
		PageBean<Note> pb = new PageBean<Note>();
		if (!Util.isEmpty(newsk)) {
			newsk = new String(newsk.getBytes("ISO-8859-1"), "UTF-8");
			pb.setKey(newsk);
		}
		if (!Util.isEmpty(topageno)) {
			pb.setNowPageNo(Integer.parseInt(topageno));
		}
		pb.setCount(countAllNote(newsk));
		pb.setSource(noteAllList(pb.getKey(), pb.getNowPageNo()));
		pb.setPageSize(15);
		pb.setPageLength();
		return pb;
	}

	@Override
	public Map<String, Object> changeNotesStatus(String nids) {
		m.clear();
		String[] s = nids.split(",");
		for (String i : s) {
			Integer id = Integer.parseInt(i);
			Note n = (Note) nDao.findById(Note.class, id);
			n.setStatus(!n.getStatus());
			nDao.update(n);
		}
		m.put("msg", "操作成功！");
		return m;
	}

}
