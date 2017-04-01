package com.reawei.controller.order;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.reawei.common.utils.MathExtend;
import com.reawei.controller.sys.BaseController;
import com.reawei.entity.RwMember;
import com.reawei.entity.RwProduct;
import com.reawei.entity.RwProductOrder;
import com.reawei.service.IRwMemberService;
import com.reawei.service.IRwProductOrderService;
import com.reawei.service.IRwProductService;
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
    private IRwProductOrderService productOrderService;
    @Resource
    private IRwMemberService memberService;
    @Resource
    private IRwProductService productService;

    @Permission("6001")
    @RequestMapping("/list")
    public String listView(){
        return "order/list";
    }

    @Permission("6001")
    @RequestMapping("/getList")
    @ResponseBody
    public String getList(){
        Page<RwProductOrder> page = getPage();
        EntityWrapper<RwProductOrder> wrapper = new EntityWrapper<RwProductOrder>();
        RwProductOrder order = new RwProductOrder();
        wrapper.setEntity(order);
        page.setOrderByField("create_time");
        page.setAsc(false);
        page = productOrderService.selectPage(page, wrapper);
        for (RwProductOrder or : page.getRecords()){
           RwMember member = memberService.selectById(or.getMemberId());
            if (member != null){
                or.setMemberName(member.getName());
            }else {
                or.setMemberName("未知用户");
            }
            RwProduct product = productService.selectById(or.getProductId());
            if (product != null) {
                or.setProductName(product.getName());
            }else {
                or.setProductName("未知商品");
            }
            or.setPrice(MathExtend.divide(or.getAmount(),100,2));
        }
        return jsonPage(page);
    }
}
