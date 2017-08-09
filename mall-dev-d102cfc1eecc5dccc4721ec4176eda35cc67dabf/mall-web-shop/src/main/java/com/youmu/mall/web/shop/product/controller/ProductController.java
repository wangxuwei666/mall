/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.product.controller;

import java.util.List;

import com.youmu.mall.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.bus.service.IBusinessTypeService;
import com.youmu.mall.bus.vo.BusinessListVo;
import com.youmu.mall.bus.vo.BusinessTypeListVo;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.service.IProductService;
import com.youmu.mall.product.vo.ProductDetailShopVo;
import com.youmu.mall.product.vo.ProductVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

/**
 * 用户端
 * @author yujiahao
 * @version $Id: ProductController.java, v 0.1 2017年2月25日 下午4:01:31 yujiahao Exp $
 */
@RestController
    @RequestMapping("/product/")
public class ProductController {
    
    @Autowired
    private IProductService productService;
    
    @Autowired
    private IBusinessTypeService businessTypeService;

    @Resource
    private ICategoryService iCategoryService;
    
    @ApiOperation(value = "用户端-查询商品详情",httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "productId", value = "商品id", required = true, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value ="getProductDetailShopVo",method=RequestMethod.GET)
    public JsonResult getProductDetailShopVo(Long productId){
        ValidateUtils.checkNotNull("商品id不能为空", productId);
        ProductDetailShopVo vo = productService.getProductDetailShopVo(productId);
        return JsonResultUtils.ok(vo);
    }
    
    @ApiOperation(value = "用户端-根据商户类别查询商品列表", httpMethod="POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "businessTypeId", value = "商户类别id", required = false, dataType="Long",  paramType = "query"),
        @ApiImplicitParam(name = "keywords", value = "商品名称", required = false, dataType="Long",  paramType = "query"),
        @ApiImplicitParam(name = "priceType", value = "价格类型(null-普通 ，1-秒杀，2-今日特价 )", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "status", value = "商品状态（默认为上架，状态码1）", required = false, dataType="Long",  paramType = "query"),
        @ApiImplicitParam(name = "isFilter", value = "是否过滤家装商户", required = false, dataType="int",  paramType = "query"),
    })
    @RequestMapping(value ="getProductVoByType", method=RequestMethod.POST)
    public JsonResult getProductVoByType(ProductQuery query) {
        Page<ProductVo> orders = productService.getProductVoByType(query);
        return JsonResultUtils.ok(orders);
    }
    
    @ApiOperation(value = "用户端-查询所有商户分类信息", httpMethod="GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "isFilter", value = "是否过滤装修商品类型", required = false, dataType = "Integer", paramType = "query"),
    })
        @RequestMapping(value ="getAllBusinessType", method=RequestMethod.GET)
    public JsonResult selectAllBusinessType(Integer isFilter) {
        List<BusinessTypeListVo> listAll = businessTypeService.listAll(isFilter);
        return JsonResultUtils.ok(listAll);
    }
    
    @ApiOperation(value = "查询商品搜索热词", httpMethod="GET")
    @RequestMapping(value ="findProductSearchHotKeywords", method=RequestMethod.GET)
    public JsonResult findProductSearchHotKeywords() {
        return JsonResultUtils.ok(productService.findProductSearchHotKeywords());
    }

    @ApiOperation(value="根据一个分类id列出分类", httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value="分类id", name="categoryId", paramType="query", dataType="int", required=false),
    })
    @RequestMapping(value = "getCategoryById", method= RequestMethod.POST)
    public JsonResult listSysUser(Integer categoryId) {
        //ValidateUtils.checkNotNull("分类id不能为空",categoryId);
        return JsonResultUtils.ok(iCategoryService.getCategoryVo(categoryId));
    }
}
