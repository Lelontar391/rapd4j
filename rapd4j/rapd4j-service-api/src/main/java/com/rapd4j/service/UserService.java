package com.rapd4j.service;

import com.rapd4j.entity.User;
/**
 * 用户接口
* @description:
* @author:Lelonta: 
* @version:
* @create:2017-2-5 下午12:07:51
 */
public interface UserService {

	/**
	 * 用户验证
	 * 
	 * @param user
	 * @return
	 */
	User authentication(User user);

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	User selectByUsername(String username);
}
