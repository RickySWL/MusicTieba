package com.musictieba.mainpage.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.musictieba.mainpage.service.impl.MainPageServiceImpl;
import com.musictieba.pojo.User;


@Controller
@RequestMapping(value = "/mainpage")
public class MainPage {
	@Autowired
	@Qualifier("mainpageService")
	private MainPageServiceImpl mService;

	
	/**
	 * 
	* @Title: mainpage
	* @Description: 跳转到主页
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/mainpage")
	public String mainpage() {
		return "mainpage/mainpage";
	}

	/**
	 * 
	* @Title: toLogin
	* @Description: 跳转到登录
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "mainpage/login";
	}

	
	/**
	 * 
	* @Title: doLogin
	* @Description: 执行登录
	* @param session
	* @param an 账号
	* @param pw 密码
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doLogin(HttpSession session,
			String an, String pw) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.putAll(mService.login(an, pw,session));
		} catch (Exception e) {
			map.put("msg", e.getMessage());
		}
		return map;

	}

	/**
	 * 
	* @Title: toRegister
	* @Description: 跳转到注册页
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String toRegister() {
		return "mainpage/register";
	}

	/**
	 * 
	* @Title: doRegister
	* @Description: 执行注册操作
	* @param user
	* @param map
	* @param session
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(User user, Map<String, Object> map, HttpSession session) {
		String pathUrl = session.getServletContext().getRealPath("photo");
		try {
			map.putAll(mService.register(user, pathUrl));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "/infomation";
	}

	/**
	 * 
	* @Title: toChangeUserInfo
	* @Description: 跳转到用户信息页
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/changeinfo", method = RequestMethod.GET)
	public String toChangeUserInfo() {
		return "mainpage/changeinfo";
	}

	/**
	 * 
	* @Title: doChangeUserInfo
	* @Description: 
	* @param session
	* @param un 昵称
	* @param em 用户名
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@ResponseBody
	@RequestMapping(value = "/changeinfo", method = RequestMethod.POST)
	public Map<String, Object> doChangeUserInfo(HttpSession session, String un, String em) {
		Map<String, Object> map = new HashMap<String, Object>();
		User userInfo = (User) session.getAttribute("ui");
		try {
			if (mService.doChangeUserInfo(un, em, userInfo)) {
				map.put("msg", "保存成功！");
			} else {
				map.put("msg", "保存失败！数据丢失");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * 
	* @Title: toChangePhoto
	* @Description: 跳转到修改头像页面
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/changephoto", method = RequestMethod.GET)
	public String toChangePhoto() {
		return "mainpage/changephoto";
	}

	/**
	 * 
	* @Title: doChangePhoto
	* @Description: 执行修改头像页面
	* @param user 用户实体类
	* @param map
	* @param session
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/changephoto", method = RequestMethod.POST)
	public String doChangePhoto(User user, Map<String, Object> map, HttpSession session) {
		String pathUrl = session.getServletContext().getRealPath("photo");
		User user4update = (User) session.getAttribute("ui");
		try {
			map.putAll(mService.changePhoto(user, pathUrl, user4update));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "/infomation";
	}

	/**
	 * 
	* @Title: toChangepwd
	* @Description: 跳转修改密码页面
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/changepwd", method = RequestMethod.GET)
	public String toChangepwd() {
		return "mainpage/changepwd";
	}

	/**
	 * 
	* @Title: doChangepwd
	* @Description: 执行修改密码操作
	* @param session
	* @param oldpwd 旧密码
	* @param newpwd 新密码
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@ResponseBody
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public Map<String, Object> doChangepwd(HttpSession session,String oldpwd, String newpwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user4pwd = (User) session.getAttribute("ui");
		try {
			map.putAll(mService.changepwd(oldpwd, newpwd, user4pwd));
			if (map.get("msg").equals("修改密码成功！请重新登录")) {
				session.removeAttribute("ui");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * 
	* @Title: logout
	* @Description: 登出
	* @param session
	* @param map
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, Map<String, Object> map) {
		session.removeAttribute("ui");
		map.put("msg", "您已退出成功!");
		return "/infomation";
	}

	/**
	 * 
	* @Title: list4User
	* @Description: 用户列表
	* @param map
	* @param newsk 关键字
	* @param topageno 跳转页数
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/userlist")
	public String list4User(Map<String, Object> map,
			@RequestParam(value = "newsk", required = false) String newsk,
			@RequestParam(value = "topageno", required = false) String topageno) {
		try {
			map.put("PageBean", mService.qureyPageUser(newsk,topageno));			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "mainpage/userlist";
	}

	/**
	 * 
	* @Title: doCancelAccount
	* @Description: 用户注销
	* @param session
	* @param map
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@RequestMapping(value = "/cancelaccount", method = RequestMethod.GET)
	public String doCancelAccount(HttpSession session, Map<String, Object> map) {
		User u = (User) session.getAttribute("ui");
		try {
			map.putAll(mService.CancelAccount(u));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return "/infomation";
	}

	/**
	 * 
	* @Title: doChangeUserStatus
	* @Description: 改变用户可登陆状态
	* @param uids 用户id组
	* @param map
	* @return
	* @author: sunweilun
	* @date: 2017年5月18日
	 */
	@ResponseBody
	@RequestMapping(value = "/changeuserstatus")
	public Map<String, Object> doChangeUserStatus(String uids, Map<String, Object> map) {
		try {
			map.putAll(mService.changeUserStatus(uids));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
