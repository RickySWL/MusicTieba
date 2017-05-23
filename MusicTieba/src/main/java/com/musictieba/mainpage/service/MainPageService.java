package com.musictieba.mainpage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.musictieba.pojo.User;
import com.musictieba.util.PageBean;

public interface MainPageService {
	/**
	 * 
	* @Title: register
	* @Description: 注册
	* @param user 用户信息
	* @param pathUrl 存储图片路径
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	Map<String, Object> register(User user, String pathUrl) throws Exception ;

	/**
	 * 
	* @Title: hasSameAccount
	* @Description: 查找相同昵称
	* @param account 账号
	* @return
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	boolean hasSameAccount(String account);

	/**
	 * 
	* @Title: savePhoto
	* @Description: 存储头像
	* @param photo 临时文件
	* @param pathUrl 存储地址
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	String savePhoto(MultipartFile photo, String pathUrl)throws Exception ;

	/**
	 * 
	* @Title: login
	* @Description: 登录
	* @param account 账号
	* @param password 密码
	* @param session 当前用户session
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	Map<String, Object> login(String account, String password,HttpSession session)throws Exception ;

	/**
	 * 
	* @Title: doChangeUserInfo
	* @Description: 修改用户信息
	* @param userName 要修改的用户名
	* @param email  要修改用户邮箱
	* @param userinfo 要修改的目标用户
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	boolean doChangeUserInfo(String userName, String email, User userinfo) throws Exception ;

	/**
	 * 
	* @Title: changePhoto
	* @Description: 修改头像
	* @param user4photo 存放头像的临时user
	* @param pathUrl 存储地址
	* @param user4update 更新目标user
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	Map<String, Object> changePhoto(User user4photo, String pathUrl, User user4update) throws Exception ;

	/**
	 * 
	* @Title: deletePhoto
	* @Description: 删除头像
	* @param oldPhotoUrl 旧头像连接
	* @param pathUrl 存储地址
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	void deletePhoto(String oldPhotoUrl, String pathUrl);

	/**
	 * 
	* @Title: changepwd
	* @Description: 修改密码
	* @param oldpwd 旧密码
	* @param newpwd 新密码
	* @param user4pwd 要修改的目标账户
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	Map<String, Object> changepwd(String oldpwd, String newpwd, User user4pwd) throws Exception ;

	/**
	 * 
	* @Title: countUser
	* @Description: 统计用户总数
	* @param sk 搜索关键字
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	int countUser(String sk) throws Exception ;

	/**
	 * 
	* @Title: userList
	* @Description: 分页查询用户
	* @param sk 搜索关键字
	* @param nowpageno 当前页码
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	List<User> userList(String sk, int nowpageno) throws Exception ;

	/**
	 * 
	* @Title: CancelAccount
	* @Description: 用户自主注销账户
	* @param u 要注销的目标账户
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	Map<String, Object> CancelAccount(User u) throws Exception ;

	/**
	 * 
	* @Title: changeUserStatus
	* @Description: 改变用户状态
	* @param uids 账户的id
	* @return 
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
	Map<String, Object> changeUserStatus(String uids) throws Exception ;

	
	/**
	 * 
	* @Title: qureyPageUser
	* @Description: 返回分页
	* @param searchkey 查询关键字
	* @param topageno  跳转页码
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月8日
	 */
    PageBean<User> qureyPageUser(String searchkey, String topageno) throws Exception;
}
