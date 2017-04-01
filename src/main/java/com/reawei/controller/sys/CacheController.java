package com.reawei.controller.sys;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.reawei.common.EhcacheHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 清楚缓存
 */
@Controller
@RequestMapping("/sys/cache")
public class CacheController extends BaseController {

    @ResponseBody
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/clean", method = RequestMethod.GET)
    public String clean() {
        EhcacheHelper.cleanAll();
        boolean ret = true;
        return toJson(ret);
    }

}
