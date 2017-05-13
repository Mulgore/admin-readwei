package com.reawei.controller.sys;


import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.IpHelper;
import com.reawei.common.utils.GetPlaceByIp;
import com.reawei.common.utils.WeatherUtil;
import com.reawei.service.IRwEhCacheService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static com.reawei.common.utils.WeatherUtil.showapiWeather;

/**
 * <p>
 * 图表演示
 * </p>
 *
 * @author hubin
 * @Date 2016-05-11
 */
@Controller
@RequestMapping("/sys/echarts")
public class EchartsController extends BaseController {

    @Resource
    private IRwEhCacheService ehCacheService;

    /**
     * 地图
     */
//    @Permission(action = Action.Skip)
//    @RequestMapping("/map")
//    public String map(Model model) {
//        String ipAddress = IpHelper.getIpAddr(this.request);
////        String ipAddress = "123.206.181.24";
//        String city = "";
//        List<Integer> lowestTemperature = new ArrayList<>();
//        List<Integer> maximumTemperature = new ArrayList<>();
//        List<String> weeks = new ArrayList<>();
//        logger.info("===================== { ipAddress= " + ipAddress + " } ==============");
//        if (!"127.0.0.1".equals(ipAddress)) {
//            Map<String, String> lonLatData = GetPlaceByIp.getlonLatByIP(ipAddress);
//
//            if (lonLatData != null) {
//                Map<String, Object> weatherData = WeatherUtil.weekWeadTher(lonLatData.get("lon"), lonLatData.get("lat"));
//                if (weatherData != null) {
//                    logger.info("===================== { today weather= " + weatherData.get("today") + " } ==============");
//                    logger.info("===================== { future weather= " + weatherData.get("future") + " } ==============");
//                    JSONObject today = (JSONObject) weatherData.get("today");
//                    JSONArray future = (JSONArray) weatherData.get("future");
//                    city = today.get("city").toString();
//                    weeks.add('\'' + today.get("week").toString() + '\'');
//                    String[] todayTemperature = today.get("temperature").toString().split("~");
//                    lowestTemperature.add(Integer.parseInt(todayTemperature[0]));
//                    maximumTemperature.add(Integer.parseInt(todayTemperature[1]));
//                    int i = 0;
//                    for (Object arr : future) {
//                        if (i < 6) {
//                            JSONObject val = (JSONObject) arr;
//                            String[] temperatures = val.get("temperature").toString().split("~");
//                            lowestTemperature.add(Integer.parseInt(temperatures[0]));
//                            maximumTemperature.add(Integer.parseInt(temperatures[1]));
//                            weeks.add('\'' + val.get("week").toString() + '\'');
//                            i++;
//                        }
//
//                    }
//                }
//            }
//        }
//        model.addAttribute("city", city);
//        model.addAttribute("lowestTemperature", lowestTemperature);
//        model.addAttribute("maximumTemperature", maximumTemperature);
//        model.addAttribute("weeks", weeks);
//        return "/echarts/map";
//    }

    /**
     * 地图
     */
    @Permission(action = Action.Skip)
    @RequestMapping("/map")
    public String map(Model model) {
        String ipAddress = IpHelper.getIpAddr(this.request);
        Map<String,Object> ret = ehCacheService.showWeather(ipAddress);
        model.addAttribute("city", ret.get("city"));
        model.addAttribute("lowestTemperature", ret.get("lowestTemperature"));
        model.addAttribute("maximumTemperature", ret.get("maximumTemperature"));
        model.addAttribute("weeks", ret.get("weeks"));
        return "/echarts/map";
    }

}
