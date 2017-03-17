package com.readwei.service.impl;

import com.readwei.entity.Product;
import com.readwei.mapper.ProductMapper;
import com.readwei.service.IProductService;
import com.readwei.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-17
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product> implements IProductService {
	
}
