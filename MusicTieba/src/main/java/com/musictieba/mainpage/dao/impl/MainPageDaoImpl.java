package com.musictieba.mainpage.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.musictieba.mainpage.dao.MainpageDao;
import com.musictieba.pojo.User;
import com.musictieba.util.Util;
import com.musictieba.util.dao.impl.BaseDaoImpl;

@Repository("mainpageDao")
public class MainPageDaoImpl extends BaseDaoImpl implements MainpageDao {

	// @Override
	// public List<User> findUsers(String whereStr) {
	// String hql = "from User";
	// if (!Util.isEmpty(whereStr)) {
	// hql += " where " + whereStr;
	// }
	// return this.findByHql(hql);
	//
	// }

	@Override
	public void addUser(User u) {
		this.add(u);
	}

	@Override
	public void updateUserInfo(User userinfo) {
		this.update(userinfo);
	}

	@Override
	public List<User> userList(int nowpageno, String key) {
		List<Object> keyList = new ArrayList<Object>();
		String hql = "";
		if (!Util.isEmpty(key)) {
			keyList.add("%" + key + "%");
			hql = "from User u where u.userName like ? and u.admin = false";
			return queryPageWithParam(hql, nowpageno, 10, keyList);
		} else {
			hql = "from User u where u.admin = false";
			return queryPageWithoutParam(hql, nowpageno, 10);
		}

	}

	@Override
	public User findUserById(Integer userid) {
		return (User) this.findById(User.class, userid);
	}

	@Override
	public User userLogin(String account, String password) {
		return this.login(account, password);
	}

	@Override
	public User findUserByAccount(String account) {
		return super.findUserByAccount(account);
	}
	
}
