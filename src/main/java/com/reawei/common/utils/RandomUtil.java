package com.reawei.common.utils;

import java.util.*;

/**
 * Created by xingwu on 2017/4/27.
 */
public class RandomUtil {

    public RandomUtil() {
    }

    public static String random(int size) {
        List<String> ranList = new ArrayList();
        int r = 10000000;
        Random random = new Random();

        for(int j = 0; j < size; ++j) {
            int a = random.nextInt(1);
            int b = random.nextInt(1);
            int c = random.nextInt(9);
            int d = random.nextInt(9);
            int e = random.nextInt(9);
            int f = random.nextInt(9);
            int g = random.nextInt(9);
            ranList.add(String.valueOf(r + a + b + c + d + e + f + g));
        }

        return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
    }

    public static String newRandomV1(int size, int price, List<String> oldRandomList) {
        List<String> randomList = new ArrayList();
        int r = 10000000;

        for(int i = 1; i <= price; ++i) {
            if(!oldRandomList.contains(String.valueOf(r + i))) {
                randomList.add(String.valueOf(r + i));
            }
        }

        List<String> ranList = new ArrayList();

        for(int j = 0; j < size; ++j) {
            if(randomList.size() > 0) {
                int ran = (new Random()).nextInt(randomList.size());
                ranList.add((String)randomList.get(ran));
                randomList.remove(ran);
            }
        }

        return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
    }

    public static String newRandom(int size, int price, List<String> oldRandomList) {
        Map<String, Integer> randomMap = new HashMap();
        int r = 10000000;

        int i;
        for(i = 1; i <= price; ++i) {
            randomMap.put(String.valueOf(r + i), new Integer(0));
        }

        for(i = 0; i < oldRandomList.size(); ++i) {
            randomMap.put(oldRandomList.get(i), new Integer(1));
        }

        List<String> ranList = new ArrayList();

        for(int j = 0; j < size; ++j) {
            if(randomMap.size() > 0) {
                int ran = (new Random()).nextInt(randomMap.size() - oldRandomList.size() - j);
                int max = Math.min(ran + oldRandomList.size() + size, randomMap.size());
                String[] keys = new String[randomMap.size()];
                randomMap.keySet().toArray(keys);
                int k = 0;

                for(int x = 0; k < max; ++k) {
                    String key = keys[k];
                    Integer flag = (Integer)randomMap.get(key);
                    if(flag.intValue() == 0) {
                        if(x == ran) {
                            ranList.add(key);
                            randomMap.put(key, new Integer(1));
                            break;
                        }

                        ++x;
                    }
                }
            }
        }

        return ranList.toString().replaceAll("\\s+", "").replace("[", "").replace("]", "");
    }

    public static void main(String[] args) {
        List<String> oldRandomList = new ArrayList();
        int r = 10000000;

        for(int i = 1; i <= 390000; ++i) {
            oldRandomList.add(String.valueOf(r + i));
        }

        String strList = newRandom(5, 400000, oldRandomList);
        System.err.println(strList);
    }
}
