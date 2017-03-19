package com.readwei.service.impl;

import com.readwei.entity.ProductOrder;
import com.readwei.mapper.ProductOrderMapper;
import com.readwei.service.IProductOrderService;
import com.readwei.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
@Service
public class ProductOrderServiceImpl extends BaseServiceImpl<ProductOrderMapper, ProductOrder> implements IProductOrderService {
	
}
