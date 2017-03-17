package com.readwei.controller.product;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.readwei.controller.BaseController;
import com.readwei.entity.Product;
import com.readwei.entity.ProductCategory;
import com.readwei.service.IProductCategoryService;
import com.readwei.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * <p>
 * 宝贝列表
 * </p>
 *
 * @author hubin
 * @Date 2016-01-06
 */
@Controller
@RequestMapping("/product/goods")
public class ProductGoodsController extends BaseController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IProductCategoryService productCategoryService;

    /**
     * 宝贝列表页面
     */
    @Permission("5002")
    @RequestMapping("/list")
    public String goodsView(Model model) {
        return "product/goods/list";
    }

    /**
     * 宝贝列表
     */
    @Permission("5002")
    @RequestMapping("/getList")
    @ResponseBody
    public String goodsGetList() {
        Page<Product> page = getPage();
        EntityWrapper<Product> wrapper = new EntityWrapper<Product>();
        Product product = new Product();
        wrapper.setEntity(product);
        page.setOrderByField("create_time");
        page.setAsc(false);
        page = productService.selectPage(page, wrapper);
        for (Product pro : page.getRecords()) {
            pro.setcName(productCategoryService.selectById(pro.getCategoryId()).getName());
        }
        return jsonPage(page);
    }
}
