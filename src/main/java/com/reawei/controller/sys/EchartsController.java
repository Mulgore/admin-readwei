package com.reawei.controller.sys;


import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.IpHelper;
import com.reawei.common.utils.GetPlaceByIp;
import com.reawei.common.utils.WeatherUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    /**
     * 地图
     */
    @Permission(action = Action.Skip)
    @RequestMapping("/map")
    public String map(Model model) {
        String ipAddress = IpHelper.getIpAddr(this.request);
//        String ipAddress = "123.206.181.24";
        Map<String, String> lonLatData = GetPlaceByIp.getlonLatByIP(ipAddress);
        String city = "";
        List<Integer> lowestTemperature = new ArrayList<>();
        List<Integer> maximumTemperature = new ArrayList<>();
        List<String> weeks = new ArrayList<>();
        if (lonLatData != null) {
            Map<String, Object> weatherData = WeatherUtil.weekWeadTher(lonLatData.get("lon"), lonLatData.get("lat"));
            if (weatherData != null) {
                logger.info("===================== { today weather= " + weatherData.get("today") + " } ==============");
                logger.info("===================== { future weather= " + weatherData.get("future") + " } ==============");
                JSONObject today = (JSONObject) weatherData.get("today");
                JSONArray future = (JSONArray) weatherData.get("future");
                city = today.get("city").toString();
                weeks.add('\'' + today.get("week").toString() + '\'');
                String[] todayTemperature = today.get("temperature").toString().split("~");
                lowestTemperature.add(Integer.parseInt(todayTemperature[0]));
                maximumTemperature.add(Integer.parseInt(todayTemperature[1]));
                int i = 0;
                for (Object arr : future) {
                    if (i < 6) {
                        JSONObject val = (JSONObject) arr;
                        String[] temperatures = val.get("temperature").toString().split("~");
                        lowestTemperature.add(Integer.parseInt(temperatures[0]));
                        maximumTemperature.add(Integer.parseInt(temperatures[1]));
                        weeks.add('\'' + val.get("week").toString() + '\'');
                        i++;
                    }

                }
            }
        }
        model.addAttribute("city", city);
        model.addAttribute("lowestTemperature", lowestTemperature);
        model.addAttribute("maximumTemperature", maximumTemperature);
        model.addAttribute("weeks", weeks);
        return "/echarts/map";
    }

}
