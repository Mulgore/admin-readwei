package com.reawei.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品订单表
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@TableName("rw_product_order")
public class RwProductOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 订单号
     */
	@TableField("order_id")
	private String orderId;
    /**
     * 状态 0 创建 1 未支付 2 已支付 3 取消
     */
	private Integer status;
    /**
     * 会员id
     */
	@TableField("member_id")
	private Long memberId;
    /**
     * 类目id
     */
	@TableField("product_id")
	private Long productId;
    /**
     * 收货地址id
     */
	@TableField("address_id")
	private Long addressId;
    /**
     * 快递id
     */
	@TableField("express_id")
	private Long expressId;
    /**
     * 销售价格
     */
	private Integer amount;
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

	@TableField(exist = false)
	private String memberName;

	@TableField(exist = false)
	private String productName;

	@TableField(exist = false)
	private Double price;

	public String getMemberName() { return memberName; }

	public void setMemberName(String memberName) { this.memberName = memberName; }

	public String getProductName() { return productName; }

	public void setProductName(String productName) { this.productName = productName; }

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getExpressId() {
		return expressId;
	}

	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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
