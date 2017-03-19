package com.readwei.controller.sys;


import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.encrypt.SaltEncoder;
import com.baomidou.mybatisplus.plugins.Page;
import com.readwei.entity.User;
import com.readwei.service.IRoleService;
import com.readwei.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * <p>
 * 用户管理相关操作
 * </p>
 *
 *
 * @Author Jack
 * @Date 2016/4/15 15:03
 */
@Controller
@RequestMapping("/perm/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Permission("2001")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/user/list";
	}

    @Permission("2001")
    @RequestMapping("/edit")
    public String edit(Model model, Long id ) {
    	if ( id != null ) {
			model.addAttribute("user", userService.selectById(id));
		}
    	model.addAttribute("roleList", roleService.selectList(null));
        return "/user/edit";
    }
    
	@ResponseBody
	@Permission("2001")
	@RequestMapping("/editUser")
	public String editUser( User user ) {
		boolean rlt = false;
		if ( user != null ) {
			user.setPassword(SaltEncoder.md5SaltEncode(user.getLoginName(), user.getPassword()));
			if ( user.getId() != null ) {
				rlt = userService.updateById(user);
			} else {
				user.setCreateTime(new Date());
				user.setLastTime(user.getCreateTime());
				rlt = userService.insert(user);
			}
		}
		return callbackSuccess(rlt);
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/getUserList")
	public String getUserList() {
		Page<User> page = getPage();
		return jsonPage(userService.selectPage(page, null));
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/delUser/{userId}")
	public String delUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
		return Boolean.TRUE.toString();
	}

	@ResponseBody
	@Permission("2001")
	@RequestMapping("/{userId}")
	public User getUser(@PathVariable Long userId) {
		return userService.selectById(userId);
	}


	/**
	 * 设置头像
	 */
	@Permission(action = Action.Skip)
	@RequestMapping(value = "/setAvatar", method = RequestMethod.GET)
	public String setAvatar() {
		return "/user/avatar";
	}
}
