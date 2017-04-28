package com.reawei.controller.address;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.reawei.controller.sys.BaseController;
import com.reawei.entity.RwAddress;
import com.reawei.service.IRwAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {

    @Resource
    private IRwAddressService addressService;

    /**
     * 用户地址列表页面
     * @return
     */
    @Permission("9001")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listView() {
        return "address/list";
    }

    /**
     * 查询用户地址列表
     * @return
     */
    @Permission("9001")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public String getList() {
        Page<RwAddress> page = getPage();
        EntityWrapper<RwAddress> wrapper = new EntityWrapper<>();
        RwAddress address = new RwAddress();
        wrapper.setEntity(address);
        page = addressService.selectPage(page,wrapper);
        return jsonPage(page);
    }
}
