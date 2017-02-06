package com.rapd4j.po;

/**
 * 
 * 
 * @project：LiaoB
 * @class：BasePo
 * @description:通用po類
 * @author：xuzn
 * @date：2017-1-12 上午11:25:39
 * @modify：
 * @version:
 * 
 */
public class BasePo {
	/**
	 * 当前分页显示数
	 */
	private int pageSize;

	/**
	 * 当前页数
	 */
	private int pageNum;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
