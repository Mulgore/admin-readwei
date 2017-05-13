package com.reawei.service;

import java.util.Map;

/**
 * <p>
 * 缓存 服务类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
public interface IRwEhCacheService {


    Map<String,Object> showWeather(String ipAddress);
}
