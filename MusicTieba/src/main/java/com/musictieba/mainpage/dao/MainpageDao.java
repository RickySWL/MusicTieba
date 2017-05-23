package com.musictieba.mainpage.dao;

import java.util.List;

import com.musictieba.pojo.User;

public interface MainpageDao {

	/**
	 * 
	* @Title: findUsers
	* @Description: 
	* @param whereStr
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	//public List<User> findUsers(String whereStr);

	/**
	 * 
	* @Title: addUser
	* @Description: 添加用户
	* @param u 添加的目标用户
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	 void addUser(User u);

	/**
	 * 
	* @Title: updateUserInfo
	* @Description: 更新用户信息
	* @param userinfo 要跟新的目标用户
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	 void updateUserInfo(User userinfo);

	/**
	 * 
	* @Title: userList
	* @Description: 分页查询用户
	* @param nowpageno 要查询的目标页
	* @param key 查询关键字
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	 List<User> userList(int nowpageno,String key);

	/**
	 * 
	* @Title: findUserById
	* @Description: 通过id查询用户
	* @param userid 用户id
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	 User findUserById(Integer userid);

	/**
	 * 
	* @Title: userLogin
	* @Description: 用户登录
	* @param account 账号
	* @param password 密码
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	 User userLogin(String account, String password);

	/**
	 * 
	* @Title: findUserByAccount
	* @Description: 通过账号查询用户
	* @param account 账号
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	 User findUserByAccount(String account);
	
}
