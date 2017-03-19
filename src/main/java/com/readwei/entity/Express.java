package com.readwei.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;
import java.io.Serializable;


/**
 * <p>
 * 物流表
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
public class Express implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 会员ID
     */
	@TableField("member_id")
	private Integer memberId;
    /**
     * 订单号ID
     */
	@TableField("order_id")
	private Integer orderId;
    /**
     * 物流号
     */
	@TableField("express_no")
	private String expressNo;
    /**
     * 状态 0 已揽件 1 运输 2 正在配送 3 已签收
     */
	private Integer status;
    /**
     * 备注
     */
	private String description;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 修改时间
     */
	@TableField("modify_time")
	private Date modifyTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
