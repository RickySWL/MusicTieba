package com.musictieba.music.controller;


import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.musictieba.music.service.impl.musicServiceImpl;
import com.musictieba.pojo.*;

@Controller
@RequestMapping(value = "/music")
public class MusicController {
	@Autowired
	@Qualifier("musicService")
	private musicServiceImpl mService;

	@Autowired
	private HttpSession session;

	/**
	 * 
	* @Title: toAddMusic
	* @Description: 跳转到添加音乐界面
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	@RequestMapping(value = "/addmusic", method = RequestMethod.GET)
	public String toAddMusic() {
		return "music/addmusic";
	}

	/**
	 * 
	* @Title: doAddMusic
	* @Description: 添加音乐
	* @param m 音乐实体类
	* @param map
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	@RequestMapping(value = "/addmusic", method = RequestMethod.POST)
	public String doAddMusic(Music m, Map<String, Object> map) {
		String musicUrl = session.getServletContext().getRealPath("musicfile");
		String coverUrl = session.getServletContext().getRealPath("photo");
		User u = (User) session.getAttribute("ui");
		try {
			if (m != null) {
				map.putAll(mService.addMusic(m, musicUrl, coverUrl, u));
			} else {
				map.put("msg", "数据丢失");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "/infomation";
	}

	/**
	 * 
	* @Title: doSearchMusic
	* @Description: 跳转到搜索音乐界面
	* @param map
	* @param newsk
	* @param topageno
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	@RequestMapping(value = "/searchmusic")
	public String doSearchMusic(Map<String, Object> map, String newsk, String topageno) {
		try {
			map.put("PageBean", mService.qureyPageMusic(newsk, topageno));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "music/searchmusic";
	}

	/**
	 * 
	* @Title: toListenAndRead
	* @Description: 跳转到听音乐
	* @param map
	* @param muiscId
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	@RequestMapping(value = "/listenandread", method = RequestMethod.GET)
	public String toListenAndRead(Map<String, Object> map, @RequestParam(value = "mid", required = true) int muiscId) {
		try {
			Music music = mService.findMuisc(muiscId);
			String musicUrl = music.getMusicUrl();
			Note n = music.getNote();		
			map.put("musicUrl", musicUrl);
			map.put("note", n);
			map.put("PageBean",mService.qureyPageComment(String.valueOf(n.getNoteId()), "1"));		
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "music/listenandread";
	}

	/**
	 * 
	* @Title: listAllNotes
	* @Description: 跳转到管理音乐界面
	* @param map
	* @param newsk
	* @param topageno
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	@RequestMapping(value = "/managemusic")
	public String listAllNotes(Map<String, Object> map, String newsk,String topageno) {
		try {
			map.put("PageBean", mService.qureyPageAllMusic(newsk,topageno));			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "music/managemusic";
	}
	
	/**
	 * 
	* @Title: doChangeMusicStatus
	* @Description: 改变音乐状态
	* @param mids
	* @param map
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	@ResponseBody
	@RequestMapping(value = "/changemusicstatus")
	public Map<String, Object> doChangeMusicStatus(String mids, Map<String, Object> map) {
		try {
			map.putAll(mService.changeMusicStatus(mids));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
