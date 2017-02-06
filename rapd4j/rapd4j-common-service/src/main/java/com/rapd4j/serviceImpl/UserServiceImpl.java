package com.rapd4j.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rapd4j.entity.User;
import com.rapd4j.entity.UserExample;
import com.rapd4j.entity.UserExample.Criteria;
import com.rapd4j.mapper.UserMapper;
import com.rapd4j.service.UserService;
/**
 * 
* @description:用户实现类
* @author:Lelonta: 
* @version:
* @create:2017-2-5 下午12:13:25
 */
@Service("userServie")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	public int insert(User model) {
		return userMapper.insertSelective(model);
	}

	public int update(User model) {
		return userMapper.updateByPrimaryKeySelective(model);
	}

	public int delete(Long id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	public User authentication(User user) {
		return userMapper.authentication(user);
	}

	public User selectById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据用户名称查询用户
	 */
	public User selectByUsername(String username) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		//where username = username
		criteria.andUsernameEqualTo(username);
//		example.createCriteria().andUsernameEqualTo(username);
		final List<User> list = userMapper.selectByExample(example);
		return list.get(0);
	}

}
