package com.musictieba.util.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.musictieba.pojo.User;

public interface BaseDao {

	/**
	 * 
	* @Title: add
	* @Description:添加记录
	* @param obj 实体类
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public void add(Object obj);
	
	/**
	 * 
	* @Title: merge
	* @Description: 合并session中存在相同id但不同值得对象
	* @param obj
	* @author: sunweilun
	* @date: 2017年5月13日
	 */
	public void merge(Object obj);

	/**
	 * 
	* @Title: update
	* @Description: 跟新记录
	* @param obj 实体类
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public void update(Object obj);

	/**
	 * 
	* @Title: delete
	* @Description: 删除记录
	* @param obj 实体类
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public void delete(Object obj);

	/**
	 * 
	* @Title: findById
	* @Description: 通过id查询
	* @param objClass 实体类
	* @param id 记录id
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public Object findById(Class objClass, Serializable id);

	/**
	 * 
	* @Title: findByHql
	* @Description: 通过hql查询
	* @param hql hql语句
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public List findByHql(String hql);

	/**
	 * 
	* @Title: countWithoutparam
	* @Description: 无参数统计记录
	* @param hql hql语句
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public long countWithoutparam(String hql);

	/**
	 * 
	* @Title: countWithParam
	* @Description: 通过参数统计记录
	* @param hql hql语句
	* @param keyList 查询关键字
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public long countWithParam(String hql,List<Object> keyList);

	/**
	 * 
	* @Title: queryPage
	* @Description: 有查询条件的分页查询
	* @param hql hql语句
	* @param nowpageno 当前页
	* @param item 每页记录数
	* @param key 搜索关键字
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public List queryPageWithParam(String hql, int nowpageno, int item,List<Object> keyList);

	/**
	 * 
	* @Title: queryPageWithoutParam
	* @Description: 无查询条件的分页查询
	* @param hql
	* @param nowpageno
	* @param item
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public List queryPageWithoutParam(String hql, int nowpageno, int item);
	/**
	 * 
	* @Title: dohql
	* @Description: 执行hql语句
	* @param hql hql语句
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public void dohql(String hql);

	/**
	 * 
	* @Title: login
	* @Description: 用户登录
	* @param ac 账户
	* @param pw 秘密
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public User login(String ac, String pw);

	/**
	 * 
	* @Title: findUserByAccount
	* @Description: 查询是否有相同账号
	* @param name 用户昵称
	* @return
	* @author: sunweilun
	* @date: 2017年5月9日
	 */
	public User findUserByAccount(String name);
	
	
	
	
	
	
}
