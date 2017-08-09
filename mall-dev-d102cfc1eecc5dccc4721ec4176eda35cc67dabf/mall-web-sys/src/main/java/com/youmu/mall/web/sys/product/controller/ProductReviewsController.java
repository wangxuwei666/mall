/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.product.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.product.query.ProductReviewsQuery;
import com.youmu.mall.product.service.IProductReviewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 商品留言/评价
 * @author zh
 * @version $Id: ProductReviewsController.java, v 0.1 2017年5月3日 下午6:37:45 zh Exp $
 */
@Api(value="商品留言/评价控制器", description="评价控制器")
@RestController
@RequestMapping("/productReviews")
public class ProductReviewsController {
    
    @Resource
    IProductReviewsService productReviewsService;
    
    @GetMapping("/remove")
    @ApiOperation(value="删除评价")
    @ApiImplicitParam(name="id", value="商品评价id", paramType="query", required=true)
    public JsonResult remove(Long id) {
        ValidateUtils.checkNotNull("id不能为空", id);
        productReviewsService.remove(id);
        return JsonResultUtils.ok();
    }
    
    
    @PostMapping("/findSysPage")
    @ApiOperation(value="分页查询", notes="{\"pageNum\": 1,\"pageSize\": 10,\"productId\": 1},\"passed\":true")
    public JsonResult findSysPage(@RequestBody ProductReviewsQuery query){
        return JsonResultUtils.ok(productReviewsService.findSysPage(query));
    }
    
}
