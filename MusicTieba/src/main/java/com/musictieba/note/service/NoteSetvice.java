package com.musictieba.note.service;

import java.util.List;
import java.util.Map;

import com.musictieba.pojo.Comment;
import com.musictieba.pojo.Note;
import com.musictieba.pojo.User;
import com.musictieba.util.PageBean;

public interface NoteSetvice {

	
	
	/**
	 * 
	* @Title: addNote
	* @Description: 添加帖子
	* @param note 帖子实体类
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月16日
	 */
	public void addNote(Note note) throws Exception ;

	
	/**
	 * 
	* @Title: countNote
	* @Description: 统计帖子数量
	* @param sk 搜索关键字
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月16日
	 */
	public int countNote(String sk) throws Exception ;
	
	/**
	 * 
	* @Title: countAllNote
	* @Description: 统计所有帖子（包括不能浏览的）
	* @param sk 搜索关键字
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public int countAllNote(String sk) throws Exception ;

	/**
	 * 
	* @Title: noteList
	* @Description: 帖子列表
	* @param sk 搜索关键字
	* @param nowpageno 跳转的页数
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public List<Note> noteList(String sk, int nowpageno) throws Exception ;
	
	/**
	 * 
	* @Title: noteAllList
	* @Description: 所有帖子的列表
	* @param sk 搜索关键字
	* @param nowpageno 跳转页数
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public List<Note> noteAllList(String sk, int nowpageno) throws Exception ;

	/**
	 * 
	* @Title: queryNote
	* @Description: 查询单个帖子
	* @param noteid 帖子ID
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public Map<String, Object> queryNote(String noteid) throws Exception ;

	/**
	 * 
	* @Title: countComment
	* @Description: 统计回复数
	* @param noteid 帖子id
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public int countComment(String noteid) throws Exception ;

	/**
	 * 
	* @Title: addComment
	* @Description: 添加回复
	* @param c 回复实体类
	* @param u 用户实体类
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public void addComment(Comment c,User u) throws Exception ;

	/**
	 * 
	* @Title: commentList
	* @Description: 回复列表
	* @param noteId 帖子id
	* @param nowpageno 跳转页数
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public List<Comment> commentList(String noteId, int nowpageno) throws Exception ;

	/**
	 * 
	* @Title: changeCommentStatus
	* @Description: 改变回复状态
	* @param cid 回复id
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public Map<String, Object> changeCommentStatus(Integer cid) throws Exception ;

	/**
	 * 
	* @Title: changeNoteStatus
	* @Description:  改变帖子状态
	* @param noteid 帖子id
	* @return 
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public Map<String, Object> changeNoteStatus(int noteid)throws Exception;
	
	/**
	 * 
	* @Title: myNoteList
	* @Description: 我的帖子列表
	* @param searchkey 搜索关键词
	* @param nowpageno 跳转页数
	* @param u 用户实体类
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public List<Note> myNoteList(String searchkey, int nowpageno,User u) throws Exception ;

	/**
	 * 
	* @Title: countMyNote
	* @Description: 统计我的帖子数量
	* @param searchkey 搜索关键字
	* @param u 实体类
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public int countMyNote(String searchkey,User u) throws Exception ;
	
	/**
	 * 
	* @Title: qureyPageNote
	* @Description: 分页查询note
	* @param searchkey 搜索关键字
	* @param topageno 跳转页数
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public PageBean<Note> qureyPageNote(String searchkey, String topageno) throws Exception;
	
	/**
	 * 
	* @Title: qureyPageComment
	* @Description: 分页查询回复
	* @param noteId 帖子ID
	* @param topageno 跳转页数
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public PageBean<Comment> qureyPageComment(String noteId, String topageno) throws Exception ;

	/**
	 * 
	* @Title: qureyPageNoteByUser
	* @Description: 分页查询我的帖子
	* @param searchkey 关键字
	* @param topageno 跳转
	* @param currentUser 当前用户
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public PageBean<Note> qureyPageNoteByUser(String searchkey, String topageno,User currentUser)throws Exception;

	/**
	 * 
	* @Title: qureyPageAllNotes
	* @Description: 分页查询所有帖子
	* @param newsk 关键字
	* @param topageno 跳转页数
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public PageBean<Note> qureyPageAllNotes(String newsk, String topageno) throws Exception;
	
	/**
	 * 
	* @Title: changeNotesStatus
	* @Description: 改变多个帖子状态
	* @param nids 组合的帖子id
	* @return
	* @author: sunweilun
	* @date: 2017年5月17日
	 */
	public Map<String, Object> changeNotesStatus(String nids) ;
}
