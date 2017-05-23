package com.musictieba.note.controller;
import java.util.Map;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.musictieba.note.service.impl.NoteServiceImlp;
import com.musictieba.pojo.*;
import com.musictieba.util.Util;

@Controller
@RequestMapping(value = "/note")
public class NoteController {

	@Autowired
	@Qualifier("noteService")
	private NoteServiceImlp nService;

	@Autowired
	private HttpSession session;

	/**
	 * 
	* @Title: toAddNote
	* @Description: 查看帖子和增加帖子页面
	* @param map
	* @param newsk 关键字
	* @param topageno 跳转页
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/listandadd", method = RequestMethod.GET)
	public String toAddNote(Map<String, Object> map,
			@RequestParam(value = "newsk", required = false) String newsk,
			@RequestParam(value = "topageno", required = false) String topageno) {
		try {
			map.put("PageBean",nService.qureyPageNote(newsk,topageno));
		}catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "note/listandadd";
	}

	/**
	 * 
	* @Title: doAddNote
	* @Description: 
	* @param note 帖子实体类
	* @param map
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/listandadd", method = RequestMethod.POST)
	public String doAddNote(Note note, Map<String, Object> map) {
		try {
			User u = (User) session.getAttribute("ui");
			if (note != null) {
				if (!Util.isEmpty(note.getTitle()) && !Util.isEmpty(note.getContent())) {
					note.setUser(u);
					nService.addNote(note);
					map.put("msg", "发帖成功！");
				} else {
					map.put("msg", "发帖失败，数据丢失！");
				}
			} else {
				map.put("msg", "发帖失败，数据丢失！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "/infomation";
	}

	/**
	 * 
	* @Title: toReadNoteAndAddCommint
	* @Description: 看帖页面
	* @param map
	* @param noteid 帖子id
	* @param topageno 跳转页
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/readandadd", method = RequestMethod.GET)
	public String toReadNoteAndAddCommint(Map<String, Object> map,
			@RequestParam(value = "noteid", required = true) String noteid,
			@RequestParam(value = "topageno", required = false) String topageno) {
		try {
			map.put("PageBean",nService.qureyPageComment(noteid,topageno));
			map.putAll(nService.queryNote(noteid));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "note/readandadd";
	}

	/**
	 * 
	* @Title: doReadNoteAndAddCommint
	* @Description: 添加回复
	* @param c 回复实体类
	* @param map
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/readandadd", method = RequestMethod.POST)
	public String doReadNoteAndAddCommint(Comment c, Map<String, Object> map) {
		try {
			User userInfo = (User) session.getAttribute("ui");
			if (c != null&&!Util.isEmpty(c.getContent())) {
				nService.addComment(c, userInfo);
				map.put("msg", "回帖成功！");
				map.put("noteId", c.getNoteId());
			} else {
				map.put("msg", "回帖失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "/infomation";
	}

	/**
	 * 
	* @Title: doDeleteComment
	* @Description: 删除回复
	* @param map
	* @param noteid 帖子id
	* @param cid 回复id
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/deletecomment")
	public String doDeleteComment(Map<String, Object> map, @RequestParam(value = "noteid", required = true) int noteid,
			@RequestParam(value = "cid", required = true) int cid) {
		try {
			map.putAll(nService.changeCommentStatus(cid));
			map.put("noteId", noteid);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "/infomation";
	}

	/**
	 * 
	* @Title: doDeleteNote
	* @Description: 删除note
	* @param map
	* @param noteid 帖子id
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/deletenote")
	public String doDeleteNote(Map<String, Object> map, @RequestParam(value = "noteid", required = true) int noteid) {
		try {
			map.putAll(nService.changeNoteStatus(noteid));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "/infomation";
	}

	/**
	 * 
	* @Title: toMyNote
	* @Description: 跳转到我的帖子页面
	* @param map
	* @param newsk 搜索关键字
	* @param topageno 跳转页
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/mynote")
	public String toMyNote(Map<String, Object> map,String newsk,String topageno) {
		try {
			User currentUser =(User) session.getAttribute("ui");
			map.put("PageBean",nService.qureyPageNoteByUser(newsk,topageno,currentUser));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "note/mynote";
	}
	
	/**
	 * 
	* @Title: listAllNotes
	* @Description: 管理帖子
	* @param map
	* @param newsk 关键字
	* @param topageno 跳转页
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/managenotes")
	public String listAllNotes(Map<String, Object> map, String newsk,String topageno) {
		try {
			map.put("PageBean", nService.qureyPageAllNotes(newsk,topageno));			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "note/managenotes";
	}
	
	/**
	 * 
	* @Title: doChangeNoteStatus
	* @Description: 改变帖子状态
	* @param nids 帖子ID
	* @param map
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@ResponseBody
	@RequestMapping(value = "/changenotestatus")
	public Map<String, Object> doChangeNoteStatus(String nids, Map<String, Object> map) {
		try {
			map.putAll(nService.changeNotesStatus(nids));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
