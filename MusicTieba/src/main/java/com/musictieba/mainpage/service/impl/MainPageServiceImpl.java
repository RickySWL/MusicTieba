package com.musictieba.mainpage.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.musictieba.mainpage.dao.impl.MainPageDaoImpl;
import com.musictieba.mainpage.service.MainPageService;
import com.musictieba.pojo.User;
import com.musictieba.util.PageBean;
import com.musictieba.util.Util;

@Service("mainpageService")
public class MainPageServiceImpl implements MainPageService {

	@Autowired
	@Qualifier("mainpageDao")
	private MainPageDaoImpl mDao;
	private Map<String, Object> m = new HashMap<String, Object>();

	@Override
	public Map<String, Object> register(User user, String pathUrl) throws Exception {

		String photoUrl = null;
		String account = user.getAccount();
		MultipartFile photo = user.getPhotoFile();
		m.clear();

		if (hasSameAccount(account)) {
			m.put("msg", "已经存在相同的账号!");
			return m;
		}

		if (photo != null) {
			photoUrl = this.savePhoto(photo, pathUrl);
			if (photoUrl.equals("您上传的不是图片")) {
				m.put("msg", photoUrl);
				return m;
			} else {
				user.setPhotoUrl(photoUrl);
			}

		}

		user.setAdmin(false);
		user.setStatus(true);
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		mDao.addUser(user);
		m.put("msg", "恭喜您，注册成功!");
		return m;

	}

	@Override
	public boolean hasSameAccount(String account) {
		User user = mDao.findUserByAccount(account);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String savePhoto(MultipartFile photo, String pathUrl) throws Exception {
		String fileName = Util.now();
		String oldName = photo.getOriginalFilename();
		if (!Util.isEmpty(oldName)) {
			String Suffix = Util.getSuffix(oldName);
			if (!Util.isPhoto(Suffix)) {
				String message = "您上传的不是图片";
				return message;
			}
			return Util.saveFile(pathUrl, fileName, Suffix, photo, "/photo/");
		} else {
			return "没有图片";
		}
	}

	@Override
	public Map<String, Object> login(String account, String password, HttpSession session) throws Exception {
		m.clear();
		User userInfo = mDao.userLogin(account, password);
		if (userInfo != null) {
			if (!userInfo.getStatus()) {
				m.put("msg", "无效账户！");
			} else {
				session.setAttribute("ui", userInfo);
				m.put("msg", "登录成功！");
			}
		} else {
			m.put("msg", "用户名或密码错误！");
		}
		return m;
	}

	@Override
	public boolean doChangeUserInfo(String userName, String email, User userinfo) throws Exception {
		if (userName != null && email != null) {
			userinfo.setUserName(userName);
			userinfo.setEmail(email);
			mDao.updateUserInfo(userinfo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Map<String, Object> changePhoto(User user4photo, String pathUrl, User user4update) throws Exception {

		MultipartFile photo = user4photo.getPhotoFile();
		if (photo != null) {
			String photoUrl = this.savePhoto(photo, pathUrl);
			if (!photoUrl.equals("没有图片")) {
				if (!photoUrl.equals("您上传的不是图片")) {
					String oldPhotoUrl = user4update.getPhotoUrl();
					this.deletePhoto(oldPhotoUrl, pathUrl);
					user4update.setPhotoUrl(photoUrl);
					mDao.update(user4update);
					m.put("msg", "更新头像成功！");
					return m;
				} else {
					m.put("msg", "更新头像失败！您上传的不是图片");
					return m;
				}
			} else {
				m.put("msg", "更新头像失败！数据丢失");
				return m;
			}
		} else {
			m.put("msg", "更新头像失败！数据丢失");
			return m;
		}
	}

	@Override
	public void deletePhoto(String oldPhotoUrl, String pathUrl) {
		try {
			File pf = new File(pathUrl + "/" + oldPhotoUrl);
			pf.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> changepwd(String oldpwd, String newpwd, User user4pwd) throws Exception {
		if (oldpwd != null && newpwd != null && user4pwd != null) {
			String oldPassword = user4pwd.getPassword();
			if (oldPassword.equals(oldpwd)) {
				user4pwd.setPassword(newpwd);
				mDao.update(user4pwd);
				m.put("msg", "修改密码成功！请重新登录");
				return m;
			} else {
				m.put("msg", "旧密码输入错误！");
				return m;
			}
		} else {
			m.put("msg", "修改失败！数据丢失");
			return m;
		}
	}

	@Override
	public int countUser(String sk) throws Exception {
		List<Object> keyList = new ArrayList<Object>();
		String hql = "";
		if (!Util.isEmpty(sk)) {
			keyList.add("%" + sk + "%");
			hql = "select count(*) from User u where u.userName like ? and admin = false";
			return (int) mDao.countWithParam(hql,keyList);
		} else {
			hql = "select count(*) from User u where u.admin = false";
			return (int) mDao.countWithoutparam(hql);
		}

	}

	public List<User> userList(String sk, int nowpageno) throws Exception {
		return mDao.userList(nowpageno, sk);
	}

	@Override
	public Map<String, Object> CancelAccount(User u) throws Exception {
		if (u != null) {
			u.setStatus(false);
			mDao.update(u);
			m.put("msg", "注销成功！");
			return m;
		} else {
			m.put("msg", "注销失败!");
			return m;
		}

	}

	@Override
	public Map<String, Object> changeUserStatus(String uids) throws Exception {
		m.clear();
		String[] s=uids.split(",");
		for(String i: s){
			Integer id=Integer.parseInt(i);
			User u = mDao.findUserById(id);
			u.setStatus(!u.getStatus());
			mDao.update(u);	
		}
		m.put("msg", "操作成功！");
		return m;
	}

	@Override
	public PageBean<User> qureyPageUser(String searchkey, String topageno) throws Exception {
		PageBean<User> pb = new PageBean<User>();
		if (!Util.isEmpty(searchkey)) {
			searchkey = new String(searchkey.getBytes("ISO-8859-1"), "UTF-8");
			pb.setKey(searchkey);
		}
		if (!Util.isEmpty(topageno)) {
			pb.setNowPageNo(Integer.parseInt(topageno));
		}
		pb.setCount(countUser(searchkey));
		pb.setSource(userList(pb.getKey(), pb.getNowPageNo()));
		pb.setPageLength();
		return pb;
	}

}
