package com.reawei.service.impl;

import com.reawei.common.utils.Query;
import com.reawei.common.utils.Result;
import com.reawei.entity.RwProduct;
import com.reawei.mapper.RwProductMapper;
import com.reawei.service.IRwProductService;
import com.reawei.service.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 宝贝表 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@Service
public class RwProductServiceImpl extends BaseServiceImpl<RwProductMapper, RwProduct> implements IRwProductService {

    @Resource
    private RwProductMapper rwProductMapper;

    @Override
    public Result<RwProduct> getRwProductQuryResult(Query<RwProduct> query) {
        Result<RwProduct> result = new Result<RwProduct>();
        result.setDataList(rwProductMapper.getProductQuery(query));
        result.setTotal(rwProductMapper.countGetProductQuery(query));
        return result;
    }
}
