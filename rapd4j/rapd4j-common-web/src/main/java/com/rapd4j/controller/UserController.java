package com.rapd4j.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rapd4j.entity.User;
import com.rapd4j.service.UserService;

/**
 * 
* @description:用户控制层
* @author:Lelonta: 
* @version:
* @create:2017-2-5 下午12:23:35
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Log logger = LogFactory.getLog(UserController.class);

	@Resource
	private UserService userService;

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, BindingResult result, Model model,
			HttpServletRequest request) {
		try {
			Subject subject = SecurityUtils.getSubject();
			// 已登陆则 跳到首页
			if (subject.isAuthenticated()) {
				return "redirect:/";
			}
			if (result.hasErrors()) {
				model.addAttribute("error", "参数错误！");
				return "login";
			}
			// 身份验证
			subject.login(new UsernamePasswordToken(user.getUsername(), user
					.getPassword()));
			// 验证成功在Session中保存用户信息
			final User authUserInfo = userService.selectByUsername(user
					.getUsername());
			request.getSession().setAttribute("userInfo", authUserInfo);
		} catch (AuthenticationException e) {
			// 身份验证失败
			model.addAttribute("error", "用户名或密码错误 ！");
			return "login";
		}
		return "redirect:/";
	}

	/**
	 * 用户退出
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		// 登出操作
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}

	/**
	 * 基于角色 标识的权限控制案例
	 */
	@RequestMapping(value = "/admin")
	@ResponseBody
	@RequiresRoles("role:admin")
	public String admin() {
		return "拥有admin角色,能访问";
	}

	/**
	 * 基于权限标识的权限控制案例
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	@RequiresPermissions("permission:create")
	public String create() {
		return "拥有user:create权限,能访问";
	}
}
