package com.reawei.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 宝贝表
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@TableName("rw_product")
public class RwProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 商品名称
     */
	private String name;
    /**
     * 状态 0 下架 1 上架
     */
	private Integer status;
    /**
     * 分类id
     */
	@TableField("category_id")
	private Long categoryId;
    /**
     * 销售价格
     */
	private Integer price;
    /**
     * 库存
     */
	private Integer amount;
    /**
     * 销售库存
     */
	@TableField("sold_amount")
	private Integer soldAmount;
    /**
     * 概述
     */
	private String summary;
    /**
     * 详情
     */
	private String description;
    /**
     * 使用橱窗 0 使用 1 不使用
     */
	@TableField("show_case")
	private Integer showCase;
    /**
     * 发票
     */
	private Integer invoice;
    /**
     * 退换货承诺
     */
	private Integer refund;
    /**
     * 是否保修
     */
	private Integer warranty;
    /**
     * 折扣：0.7
     */
	private Float discount;
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
	private String cName;

	@TableField(exist = false)
	private Double prices;

	public String getcName() { return cName; }

	public void setcName(String cName) { this.cName = cName; }

	public Double getPrices() { return prices; }

	public void setPrices(Double prices) { this.prices = prices; }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getSoldAmount() {
		return soldAmount;
	}

	public void setSoldAmount(Integer soldAmount) {
		this.soldAmount = soldAmount;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getShowCase() {
		return showCase;
	}

	public void setShowCase(Integer showCase) {
		this.showCase = showCase;
	}

	public Integer getInvoice() {
		return invoice;
	}

	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}

	public Integer getRefund() {
		return refund;
	}

	public void setRefund(Integer refund) {
		this.refund = refund;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
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
