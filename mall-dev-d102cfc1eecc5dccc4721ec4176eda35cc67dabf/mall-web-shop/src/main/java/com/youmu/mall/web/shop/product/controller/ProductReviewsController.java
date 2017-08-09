/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.shop.product.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.product.domain.ProductReviews;
import com.youmu.mall.product.query.ProductReviewsQuery;
import com.youmu.mall.product.service.IProductReviewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品评价控制器.
 * @author zh
 * @version $Id: ProductReviewsController.java, v 0.1 2017年5月3日 下午4:47:28 zh Exp $
 */
@RestController
@Api(value = "商品评价控制器", description="商品评价控制器")
@RequestMapping(value = "/productReviews")
public class ProductReviewsController {
    
    @Resource
    private IProductReviewsService productReviewsService;
    
    @PostMapping("/save")
    @ApiOperation(value="添加一个留言/评论", notes="{\"product\": {\"id\": 1},\"text\": \"安佛阿森纳法\"}")
    public JsonResult save(@RequestBody ProductReviews reviews) {
        // 校验参数
        ValidateUtils.checkNotNull("商品id不能为空", reviews.getProduct(), reviews.getProduct().getId());
        ValidateUtils.checkNotBlank(reviews.getText(), "留言的内容不能为空");
        productReviewsService.save(reviews);
        return JsonResultUtils.ok();
    }
    
    @PostMapping("/findPage")
    @ApiOperation(value="分页查询商品的留言/评价", notes="{\"lastGmtCreate\":\"2017-05-03 21:00:00\",\"lastId\":5,\"pageSize\": 10,\"productId\": 1} \n "
            + "第一页 lastGmtCreate 和 lastId不传 后台每一页的加载便将之前也的 最后一条数据的gmtCreate 和 id当做该参数传入")
    public JsonResult findPage(@RequestBody ProductReviewsQuery query) {
        ValidateUtils.checkNotNull("参数不能为空", query.getLastGmtCreate(), query.getLastId());
        return JsonResultUtils.ok(productReviewsService.findPage(query));
    }
}
