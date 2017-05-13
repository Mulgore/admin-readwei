package com.reawei.service.impl;

import com.reawei.service.IRwEhCacheService;
import net.sf.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.reawei.common.utils.WeatherUtil.showapiWeather;

/**
 * <p>
 * 缓存 服务实现类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@Service
public class RwEhCacheServiceImpl implements IRwEhCacheService {

    @Cacheable(value = "showWeatherCache", key = "#ipAddress")
    @Override
    public Map<String, Object> showWeather(String ipAddress) {
        Map<String, Object> rets = new HashMap<>();
        String city = "";
        List<String> lowestTemperature = new ArrayList<>();
        List<String> maximumTemperature = new ArrayList<>();
        List<String> weeks = new ArrayList<>();
        if ("127.0.0.1".equals(ipAddress)) {
            ipAddress = "";
        }
        try {
            Map<String, Object> ret = showapiWeather(ipAddress);
            city = ret.get("city").toString();
            for (int i = 1; i < 8; i++) {
                maximumTemperature.add(JSONObject.fromObject(ret.get("f" + i)).get("day_air_temperature").toString());
                lowestTemperature.add(JSONObject.fromObject(ret.get("f" + i)).get("night_air_temperature").toString());
                String week = "";
                switch (dayForWeek(JSONObject.fromObject(ret.get("f" + i)).get("day").toString())) {
                    case 1:
                        week = "\'星期一\'";
                        break;
                    case 2:
                        week = "\'星期二\'";
                        break;
                    case 3:
                        week = "\'星期三\'";
                        break;
                    case 4:
                        week = "\'星期四\'";
                        break;
                    case 5:
                        week = "\'星期五\'";
                        break;
                    case 6:
                        week = "\'星期六\'";
                        break;
                    case 7:
                        week = "\'星期日\'";
                }
                weeks.add(week);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        rets.put("city", city);
        rets.put("lowestTemperature", lowestTemperature);
        rets.put("maximumTemperature", maximumTemperature);
        rets.put("weeks", weeks);
        return rets;
    }

    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }
}
