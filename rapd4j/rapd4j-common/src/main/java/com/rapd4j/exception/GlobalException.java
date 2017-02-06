package com.rapd4j.exception;

import org.springframework.dao.DataAccessException;

/**
 * 总的异常拦截提示
 * @author duenjie
 *
 */
public class GlobalException extends DataAccessException {

	private static final long serialVersionUID = 4368133818166756376L;
	public GlobalException(String msg) {
		super(msg);
	}
	public GlobalException(String msg,Throwable cause) {
		super(msg,cause);
	}

}
