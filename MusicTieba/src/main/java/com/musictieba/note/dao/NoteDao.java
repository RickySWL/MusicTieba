package com.musictieba.note.dao;

import java.util.List;

import com.musictieba.pojo.Comment;
import com.musictieba.pojo.Note;
import com.musictieba.pojo.User;

public interface NoteDao {

	
	/**
	 * 
	* @Title: myNoteList
	* @Description: 我的帖子列表
	* @param searchkey 关键字
	* @param nowpageno 跳转页数
	* @param u 用户
	* @return
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public List<Note> myNoteList(String searchkey,int nowpageno,User u) ;
	
	



}
