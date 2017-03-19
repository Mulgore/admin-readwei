package com.readwei.service.impl;

import com.readwei.entity.Address;
import com.readwei.mapper.AddressMapper;
import com.readwei.service.IAddressService;
import com.readwei.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<AddressMapper, Address> implements IAddressService {
	
}
