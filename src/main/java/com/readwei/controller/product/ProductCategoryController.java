package com.readwei.controller.product;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.readwei.controller.sys.BaseController;
import com.readwei.entity.ProductCategory;
import com.readwei.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author xingwu
 * @since 2017-03-17
 */
@Controller
@RequestMapping("/product/category")
public class ProductCategoryController extends BaseController{

    @Autowired
    private IProductCategoryService productCategoryService;

    /**
     * 分类列表页面
     */
    @Permission("5002")
    @RequestMapping("/list")
    public String categoryView(Model model) {
        return "product/category/list";
    }

    /**
     * 分类列表
     */
    @Permission("5002")
    @RequestMapping("/getList")
    @ResponseBody
    public String categoryGetList() {
        Page<ProductCategory> page = getPage();
        EntityWrapper<ProductCategory> wrapper = new EntityWrapper<ProductCategory>();
        ProductCategory category = new ProductCategory();
        category.setPid(0);
        wrapper.setEntity(category);
        page.setOrderByField("create_time");
        page.setAsc(false);
        return jsonPage(productCategoryService.selectPage(page,wrapper));
    }
}
