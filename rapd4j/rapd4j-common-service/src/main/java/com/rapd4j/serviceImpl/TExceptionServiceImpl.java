package com.rapd4j.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapd4j.entity.TException;
import com.rapd4j.mapper.TExceptionMapper;
import com.rapd4j.service.TExceptionService;
/**
 * 
* @description:捕获异常实现层
* @author:Lelonta: 
* @version:
* @create:2017-2-5 下午12:14:08
 */
@Service("tExceptionService")
public class TExceptionServiceImpl implements TExceptionService {

	@Autowired
	private TExceptionMapper tExceptionMapper;

	/**
	 * 插入异常
	 */
	@Override
	public int txInsertException(TException tException) {

		int count = tExceptionMapper.insert(tException);
		return count;
	}

}
