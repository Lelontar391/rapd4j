package com.rapd4j.service;

import java.util.List;

import com.rapd4j.entity.Role;

/**
 * 
* @description:角色接口
* @author:Lelonta: 
* @version:
* @create:2017-2-5 下午12:06:58
 */
public interface RoleService {
	
	/**
	 * 通过用户id 查询用户 拥有的角色
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> selectRolesByUserId(Long userId);
}
