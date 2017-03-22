package com.readwei.controller.product;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.readwei.common.utils.MathExtend;
import com.readwei.controller.sys.BaseController;
import com.readwei.entity.Product;
import com.readwei.entity.ProductCategory;
import com.readwei.entity.ProductImage;
import com.readwei.service.IProductCategoryService;
import com.readwei.service.IProductImageService;
import com.readwei.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    @Autowired
    private IProductImageService productImageService;

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
        for (Product pro : page.getRecords()) { // 迭代遍历
            pro.setcName(productCategoryService.selectById(pro.getCategoryId()).getName());
            pro.setPrices(MathExtend.divide(pro.getPrice(), 100, 2));
        }
        return jsonPage(page);
    }

    /**
     * 添加宝贝页面
     *
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "add/view", method = RequestMethod.GET)
    public String goodsAddView(Model model) {
        EntityWrapper<ProductCategory> wrapper = new EntityWrapper<ProductCategory>();
        ProductCategory category = new ProductCategory();
        category.setPid(0);
        wrapper.setEntity(category);
       model.addAttribute(productCategoryService.selectList(wrapper));
        return "product/goods/add";
    }

    /**
     * 保存宝贝实现
     *
     * @param product 宝贝信息
     * @return
     */
    @Permission("5002")
    @RequestMapping(value = "add/do", method = RequestMethod.POST)
    @ResponseBody
    public String goodsSave(Product product) {
        boolean rlt = false;
        product.setCreateTime(new Date());
        product.setModifyTime(new Date());
        rlt = productService.insert(product);
        if (!rlt) {
            return callbackFail("商品保存存失败！！！");
        }
        EntityWrapper<Product> wrapper = new EntityWrapper<Product>();
        wrapper.setEntity(product);
        Product checkProduct = productService.selectOne(wrapper);
        String[] imgUrls = this.request.getParameterValues("imgUrl");
        ProductImage productImage = new ProductImage();
        productImage.setPId(checkProduct.getId());
        for (String url : imgUrls) {
            productImage.setCreateTime(new Date());
            productImage.setModifyTime(new Date());
            productImage.setImageUrl(url);
            rlt = productImageService.insert(productImage);
        }
        return callbackSuccess(rlt);
    }
}
