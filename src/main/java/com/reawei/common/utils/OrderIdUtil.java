package com.reawei.common.utils;


import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * Created by xingwu on 2017/4/27.
 */
public class OrderIdUtil {

    public OrderIdUtil() {
    }

    public static String createOrderId() {
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        String time = String.valueOf(System.currentTimeMillis());
        String hash = String.valueOf("".hashCode());
        String random = RandomUtil.random(10);
        String md5 = MD5Util.encode(pid + time + hash + random);
        return DateUtil.dateTimeToStr(new Date()) + md5.substring(0, 5).toLowerCase();
    }

    public static String createOrderId(Integer uid) {
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        String time = String.valueOf(System.currentTimeMillis());
        String hash = String.valueOf("".hashCode());
        String random = RandomUtil.random(10);
        String md5 = MD5Util.encode(uid + pid + time + hash + random);
        return DateUtil.dateTimeToStr(new Date()) + uid + md5.substring(0, 5).toLowerCase();
    }

    public static String createShortOrderId(Integer uid) {
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        String time = String.valueOf(System.currentTimeMillis());
        String hash = String.valueOf("".hashCode());
        String random = RandomUtil.random(10);
        String md5 = MD5Util.encode(uid + pid + time + hash + random);
        return DateUtil.dateTimeToStr(new Date()) + md5.substring(0, 5).toLowerCase();
    }

    @Test
    public void t() {
        String orderId = createOrderId();
        String orderUid = createOrderId(Integer.valueOf(1001639517));
        String shortOrderId = createOrderId(Integer.valueOf(1232133));
        System.out.println(orderId + "----" + orderId.length());
        System.out.println(orderUid + "----" + orderUid.length());
        System.out.println(shortOrderId + "----" + shortOrderId.length());
        System.out.println(MD5Util.encode("11223" + RandomUtil.random(3)).substring(0, 8).toLowerCase() + "--" + MD5Util.encode("11223" + RandomUtil.random(3)).substring(0, 8).toLowerCase().length());
    }
}
