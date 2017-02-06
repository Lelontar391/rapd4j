package com.rapd4j.service;

import java.util.List;

import com.rapd4j.entity.Permission;

/**
 * 
* @description:权限接口
* @author:Lelonta: 
* @version:
* @create:2017-2-5 下午12:06:45
 */
public interface PermissionService {

	/**
	 * 通过角色id 查询角色 拥有的权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> selectPermissionsByRoleId(Long roleId);

}
