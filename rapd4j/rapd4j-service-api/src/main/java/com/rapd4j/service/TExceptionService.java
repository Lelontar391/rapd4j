package com.rapd4j.service;

import com.rapd4j.entity.TException;

/**
 * 
* @description:异常接口
* @author:Lelonta: 
* @version:
* @create:2017-2-5 下午12:07:37
 */
public interface TExceptionService {

	/**
	 * 插入异常
	 * 
	 * @description:
	 * @author:xuzn
	 * @date:2017-1-24 上午10:55:32
	 * @modify:
	 * @param exception
	 * @return
	 * @version:
	 * 
	 */
	public int txInsertException(TException tException);
}
