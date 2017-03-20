package com.readwei.controller.order;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.readwei.controller.sys.BaseController;
import com.readwei.entity.ProductOrder;
import com.readwei.service.IProductOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author xingwu
 * @since 2017-03-19
 */
@Controller
@RequestMapping("/order")
public class ProductOrderController extends BaseController{

    @Resource
    private IProductOrderService  productOrderService;

    @Permission("6001")
    @RequestMapping("/list")
    public String listView(){
        return "order/list";
    }

    @Permission("6001")
    @RequestMapping("/getList")
    @ResponseBody
    public String getList(){
        Page<ProductOrder> page = getPage();
        EntityWrapper<ProductOrder> wrapper = new EntityWrapper<ProductOrder>();
        ProductOrder order = new ProductOrder();
        wrapper.setEntity(order);
        page.setOrderByField("create_time");
        page.setAsc(false);
        return jsonPage(productOrderService.selectPage(page, wrapper));
    }
}
