package com.reawei.mapper;

import com.reawei.common.utils.Query;
import com.reawei.entity.RwProduct;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 宝贝表 Mapper 接口
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */

public interface RwProductMapper extends BaseMapper<RwProduct> {

    List<RwProduct> getProductQuery(Query<RwProduct> query);

    int countGetProductQuery(Query<RwProduct> query);

}