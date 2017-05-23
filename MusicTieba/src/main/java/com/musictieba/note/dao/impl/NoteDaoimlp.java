package com.musictieba.note.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.musictieba.note.dao.NoteDao;
import com.musictieba.pojo.Comment;
import com.musictieba.pojo.Note;
import com.musictieba.pojo.User;
import com.musictieba.util.Util;
import com.musictieba.util.dao.impl.BaseDaoImpl;

@Repository("noteDao")
public class NoteDaoimlp extends BaseDaoImpl implements NoteDao{


	@Override
	public List<Note> myNoteList(String searchkey,int nowpageno,User u) {
		String hql = "";
		List<Object> keyList = new ArrayList<Object>();
		if (!Util.isEmpty(searchkey)) {
			keyList.add(searchkey);
			hql = " FROM note n WHERE n.status = true AND n.title = ? AND n.user = ?";
			return queryPageWithParam(hql, nowpageno, 15, keyList);
		}else{
			keyList.add(String.valueOf(u.getUserId()));
			hql = " FROM note n WHERE n.status = true AND  n.user = ?";
			return queryPageWithParam(hql, nowpageno, 15, keyList);
		}
	}

}
