package com.musictieba.util.dao.impl;

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
import com.musictieba.util.Util;
import com.musictieba.util.dao.BaseDao;

public class BaseDaoImpl implements BaseDao {

	@Autowired
	// Autowired(自动注入)Qualifier(适格者)标记后面的对象sesssionFactroy(session工厂)，此对象在sprint-config配置文件中已经被配置完成
	@Qualifier("sessionFactory")
	private SessionFactory sf;

	private Session getSession() {
		return sf.getCurrentSession();// 如果配置了事物，则使用sf.getCurrentSession()获取Session
	}

	@Override
	public void add(Object obj) {
		Session se = this.getSession();
		se.save(obj);
	}

	@Override
	public void update(Object obj) {
		Session se = this.getSession();
		// Transaction tr = se.beginTransaction();
		se.update(obj);
		// tr.commit();
	}

	@Override
	public void delete(Object obj) {
		Session se = this.getSession();
		// Transaction tr = se.beginTransaction();
		se.delete(obj);
		// tr.commit();
	}

	@Override
	public Object findById(Class objClass, Serializable id) {
		Session se = this.getSession();
		return se.get(objClass, id);
	}

	@Override
	public List findByHql(String hql) {
		Session se = this.getSession();
		Query q = se.createQuery(hql);
		List List = q.list();
		return List;
	}

	@Override
	public long countWithoutparam(String hql) {
		Session se = this.getSession();
		Query q = se.createQuery(hql);
		return (long) q.uniqueResult();
	}

	@Override
	public long countWithParam(String hql,List<Object> keyList) {
		Session se = this.getSession();
		Query q = se.createQuery(hql);
		for (int i = 0; i < keyList.size(); i++) {
			q.setParameter(i, keyList.get(i));
		}
		return (long) q.uniqueResult();
	}

	@Override
	public List queryPageWithParam(String hql, int nowpageno, int item, List<Object> keyList) {
		Session se = this.getSession();
		Query q = se.createQuery(hql);
		for (int i = 0; i < keyList.size(); i++) {
			q.setParameter(i, keyList.get(i));
		}
		q.setFirstResult((nowpageno - 1) * item);
		q.setMaxResults(item);
		List list = q.list();
		return list;

	}

	@Override
	public List queryPageWithoutParam(String hql, int nowpageno, int item) {
		Session se = this.getSession();
		Query q = se.createQuery(hql);
		q.setFirstResult((nowpageno - 1) * item);
		q.setMaxResults(item);
		List list = q.list();
		return list;
	}

	@Override
	public void dohql(String hql) {
		Session se = this.getSession();
		se.createQuery(hql).executeUpdate();
	}

	@Override
	public User login(String ac, String pw) {
		Session se = this.getSession();
		Criteria criteria = (Criteria) se.createCriteria(User.class);
		criteria.add(Restrictions.eq("account", ac));
		criteria.add(Restrictions.eq("password", pw));
		return (User) criteria.uniqueResult();
	}

	@Override
	public User findUserByAccount(String account) {
		Session se = this.getSession();
		Criteria criteria = (Criteria) se.createCriteria(User.class);
		criteria.add(Restrictions.eq("account", account));
		return (User) criteria.uniqueResult();
	}

	@Override
	public void merge(Object obj) {
		Session se = this.getSession();
		se.merge(obj);
	}

	

}