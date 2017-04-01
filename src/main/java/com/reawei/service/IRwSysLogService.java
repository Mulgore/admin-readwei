package com.reawei.service;

import com.baomidou.mybatisplus.service.IService;
import com.reawei.entity.RwSysLog;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface IRwSysLogService extends IService<RwSysLog> {

    void saveLog(ProceedingJoinPoint joinPoint, String methodName, String operate);
}