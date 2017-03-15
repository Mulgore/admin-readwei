package com.readwei.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户表
 *
 */
@TableName(value = "user")
public class User implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	@TableId
	private Long id;

	/** 登录名称 */
	@TableField(value = "login_name")
	private String loginName;

	/** 密码 */
	private String password;

	/** 邮箱 */
	private String email;

	/** 0、普通用户 1、管理员 */
	private Integer type;

	/** 0、禁用 1、正常 */
	private Integer status;

	/** 创建时间 */
	@TableField(value = "create_time")
	private Date createTime;

	/** 最后登录时间 */
	@TableField(value = "last_time")
	private Date lastTime;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date crTime) {
		this.createTime = crTime;
	}

	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

}
