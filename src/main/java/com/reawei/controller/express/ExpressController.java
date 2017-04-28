package com.reawei.controller.express;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.reawei.controller.sys.BaseController;
import com.reawei.entity.RwExpress;
import com.reawei.service.IRwExpressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * 物流表 前端控制器
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
@Controller
@RequestMapping("/express")
public class ExpressController extends BaseController {

    @Resource
    private IRwExpressService expressService;


    /**
     * 物流列表页面
     * @return
     */
    @Permission("8001")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listView() {
        return "/express/list";
    }

    /**
     * 查询物流列表
     * @return
     */
    @Permission("8001")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public String getList() {
        Page<RwExpress> page = getPage();
        EntityWrapper<RwExpress> wrapper = new EntityWrapper<>();
        RwExpress express = new RwExpress();
        wrapper.setEntity(express);
        page = expressService.selectPage(page,wrapper);
        return jsonPage(page);
    }

}
