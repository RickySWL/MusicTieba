package com.musictieba.util;

import java.util.List;

public class PageBean<T> {
	public final static int DEFAULT_PAGE_SIZE = 10;// 默认每页的记录数
	public final static int DEFAULT_PAGE_NO = 1;// 默认当前页

	private int pageSize = DEFAULT_PAGE_SIZE;// 每页显示记录数
	private int nowPageNo = DEFAULT_PAGE_NO; // 当前页数
	private int count; // 总的记录数
	private List<T> source;// 分页返回数据
	private String key;// 查询条件关键字
	private int pageLength;// 总共有好多页

	public PageBean() {

	}

	public PageBean(int pageSize, int nowPageNo, int count, List<T> source, String key) {
		this.nowPageNo = nowPageNo > 0 ? nowPageNo : DEFAULT_PAGE_NO;
		this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
		this.count = count;
		this.source = source;
		this.key = key;
		if (this.count % this.pageSize == 0) {
			this.pageLength = this.count / this.pageSize;
		} else {
			this.pageLength = (this.count / this.pageSize) + 1;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
	}

	public int getNowPageNo() {
		return nowPageNo;
	}

	public void setNowPageNo(int nowPageNo) {
		this.nowPageNo = nowPageNo > 0 ? nowPageNo : DEFAULT_PAGE_NO;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getSource() {
		return source;
	}

	public void setSource(List<T> source) {
		this.source = source;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 
	 * @Title: setPageLength
	 * @Description:设置页码总数 ，必须在得到记录总数后才能调用
	 * @author: sunweilun
	 * @date: 2017年5月9日
	 */
	public void setPageLength() {
		if (count % pageSize == 0) {
			pageLength = count / pageSize;
		} else {
			pageLength = (count / pageSize) + 1;
		}
	}

	public int getPageLength() {
		return pageLength;
	}

	@Override
	public String toString() {
		return "PageBean [pageSize=" + pageSize + ", nowPageNo=" + nowPageNo + ", count=" + count + ", key=" + key + ", pageLength=" + pageLength + "]";
	}
	
	
}
