package com.rapd4j.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rapd4j.entity.Role;
import com.rapd4j.mapper.RoleMapper;
import com.rapd4j.service.RoleService;

/**
 * 
* @description:捕获异常实现层
* @author:Lelonta: 
* @version:
* @create:2017-2-5 下午12:14:48
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper;

	public List<Role> selectRolesByUserId(Long userId) {
		return roleMapper.selectRolesByUserId(userId);
	}

}
