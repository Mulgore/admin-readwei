package com.reawei.service;

import com.reawei.common.utils.Query;
import com.reawei.common.utils.Result;
import com.reawei.entity.RwProduct;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 宝贝表 服务类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
public interface IRwProductService extends IService<RwProduct> {

    /**
     * 查询商品列表
     * @param query 商品Query
     * @return
     */
    Result<RwProduct> getRwProductQuryResult(Query<RwProduct> query);


}
