package com.reawei.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类型转换工具类
 * Created by xingwu on 2017/3/28.
 */
public class TypeUtil {
    protected static final Log _log = LogFactory.getLog(TypeUtil.class);

    public TypeUtil() {
    }

    public static List setToList(Set s) {
        ArrayList list = new ArrayList();
        if(!s.isEmpty()) {
            list.addAll(s);
        }

        return list;
    }

    public static Set listToSet(List l) {
        HashSet set = new HashSet();
        if(!l.isEmpty()) {
            set.addAll(l);
        }

        return set;
    }

    public static int strToInt(String str) {
        return strToInt(str, 0);
    }

    public static int strToInt(String str, int def) {
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("String 转 int 类型失败: " + var3);
            }

            return def;
        }
    }

    public static long strToLong(String str) {
        return strToLong(str, 0L);
    }

    public static long strToLong(String str, long def) {
        try {
            return Long.parseLong(str.trim());
        } catch (Exception var4) {
            if(_log.isDebugEnabled()) {
                _log.debug("String 转 long 类型失败: " + var4);
            }

            return def;
        }
    }

    public static float strToFloat(String str) {
        return strToFloat(str, 0.0F);
    }

    public static float strToFloat(String str, float def) {
        try {
            return Float.parseFloat(str.trim());
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("String 转 float 类型失败: " + var3);
            }

            return def;
        }
    }

    public static float doubleToFloat(Double d) {
        return doubleToFloat(d, 0.0F);
    }

    public static float doubleToFloat(Double d, float def) {
        try {
            return Float.parseFloat(d.toString());
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("Double 转 float 类型失败: " + var3);
            }

            return def;
        }
    }

    public static float longToFloat(Long l) {
        return longToFloat(l, 0.0F);
    }

    public static float longToFloat(Long l, float def) {
        try {
            return Float.parseFloat(l.toString());
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("Long 转 float 类型失败: " + var3);
            }

            return def;
        }
    }

    public static double strToDouble(String str) {
        return strToDouble(str, 0.0D);
    }

    public static double strToDouble(String str, double def) {
        try {
            return Double.parseDouble(str.trim());
        } catch (Exception var4) {
            if(_log.isDebugEnabled()) {
                _log.debug("String 转 double 类型失败: " + var4);
            }

            return def;
        }
    }

    public static Integer strToInteger(String str) {
        return strToInteger(str, (Integer)null);
    }

    public static Integer intToInteger(int i, Integer def) {
        try {
            return new Integer(i);
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("int 转 Integer 类型失败: " + var3);
            }

            return def;
        }
    }

    public static Integer intToInteger(int i) {
        return intToInteger(i, Integer.valueOf(0));
    }

    public static int IntegerToInt(Integer i, int def) {
        try {
            return (new Integer(i.intValue())).intValue();
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("Integer 转 int 类型失败: " + var3);
            }

            return def;
        }
    }

    public static int IntegerToInt(Integer i) {
        return IntegerToInt(i, 0);
    }

    public static Integer strToInteger(String str, Integer def) {
        try {
            return Integer.decode(str.trim());
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("String 转 Integer 类型失败: " + var3);
            }

            return def;
        }
    }

    public static String integerToStr(Integer def, String str) {
        try {
            return def.toString();
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("Integer 转 String 类型失败: " + var3);
            }

            return str;
        }
    }

    public static String integerToStr(Integer def) {
        return integerToStr(def, "");
    }

    public static String intToStr(int value) {
        Integer integer = new Integer(value);
        return integer.toString();
    }

    public static String floatToStr(float value) {
        Float floatee = new Float(value);
        return floatee.toString();
    }

    public static double intToDouble(int value) {
        return (double)value;
    }

    public static String longToStr(Long l) {
        return longToStr(l, "");
    }

    public static String longToStr(Long l, String def) {
        try {
            return Long.toString(l.longValue());
        } catch (Exception var3) {
            if(_log.isDebugEnabled()) {
                _log.debug("Long 转 String 类型失败: " + var3);
            }

            return def;
        }
    }
}
