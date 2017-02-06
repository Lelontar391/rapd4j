package com.rapd4j.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rapd4j.entity.Permission;
import com.rapd4j.mapper.PermissionMapper;
import com.rapd4j.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionMapper permissionMapper;

	
	public List<Permission> selectPermissionsByRoleId(Long roleId) {
		return permissionMapper.selectPermissionsByRoleId(roleId);
	}
}
