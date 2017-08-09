/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.test.product;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import com.google.common.collect.Lists;
import com.youmu.mall.order.dao.ProductOrderDao;
import com.youmu.mall.order.domain.ProductOrderItem;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.youmu.common.page.utils.Page;
import com.youmu.mall.product.domain.ProductReviews;
import com.youmu.mall.product.query.ProductReviewsQuery;
import com.youmu.mall.product.service.IProductReviewsService;
import com.youmu.mall.test.BaseTest;
import com.youmu.mall.user.dao.UserDao;
import com.youmu.mall.user.domain.User;
import com.youmu.mall.user.service.IUserService;
import com.youmu.mall.web.shop.product.controller.ProductReviewsController;

/**
 * 测试商品评论
 * @author zh
 * @version $Id: ProductReviewsTest.java, v 0.1 2017年5月3日 下午3:47:56 zh Exp $
 */
public class ProductReviewsTest extends BaseTest {
    
    @Resource
    private IProductReviewsService productReviewsService;
    
    @Resource
    private UserDao userDao;

    @Resource
    private ProductOrderDao productOrderDao;
    
    MockMvc mockMvc;
    
    @org.junit.Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductReviewsController()).build();
    }
    
    
    @Test
    public void testSave() throws Exception {
        ProductReviews reviews = new ProductReviews();
//        product.setId(1L);
//        reviews.setProduct(product);
        User user = new User();
        user.setId(1L);
        user.setMobile("15708437406");
        reviews.setUser(user);
        reviews.setText("aaaaa");
        productReviewsService.save(reviews);
    }
    
    @Test
    public void testRemove() throws Exception {
        productReviewsService.remove(1L);
    }
    
    @Test
    public void testFindPage() throws Exception {
        int pageSize = 10;
        ProductReviewsQuery pageQuery = new ProductReviewsQuery();
        pageQuery.setPageSize(pageSize);
        pageQuery.setProductId(213L);
        pageQuery.setLastGmtCreate(DateUtils.parseDate("2017-05-03 19:52:35", "yyyy-MM-dd HH:mm:ss"));
        pageQuery.setLastId(12L);
        List<ProductReviews> list = productReviewsService.findPage(pageQuery);
        System.err.println("size : "  + list.size());
        System.err.println(JSON.toJSONString(list));
    }

    @Test
    public void testFindSysPage() throws Exception {
        int pageNum = 1;
        int pageSize = 10;
        ProductReviewsQuery pageQuery = new ProductReviewsQuery();
        pageQuery.setPageNum(pageNum);
        pageQuery.setPageSize(pageSize);
        pageQuery.setKeywords("a");
        pageQuery.setGmtCreateStart(DateUtils.parseDate("2017-05-03", "yyyy-MM-dd"));
        pageQuery.setGmtCreateEnd(DateUtils.parseDate("2017-05-04", "yyyy-MM-dd"));
        Page<ProductReviews> page = productReviewsService.findSysPage(pageQuery);
        System.err.println(JSON.toJSONString(page));
        for (ProductReviews  p: page.getData()) {
            System.err.println(p.getProduct());
        }
    }
    
    @Test
    public void testFindPage1() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/productReviews/findPage").contentType(MediaType.APPLICATION_JSON_UTF8)
            .content("{\"lastGmtCreate\":\"2017-05-03 23:00:00\",\"lastId\":5,\"pageSize\": 10,\"productId\": 1}").
            accept(MediaType.APPLICATION_JSON)).andReturn();
        System.err.println(result.getResponse().getContentAsString());
    }
    
    @Test
    public void testSaveEmoji() throws Exception {
        User user = new User();
        user.setMobile("15708437406");
        user.setUsername("\\xF0\\x9F\\x98\\x80 \\xF0...");
        user.setId(175L);
        userDao.updateUserWxAccountInfo(user);
    }

    @Test
    public void testOrder() throws Exception {
        List<ProductOrderItem> items = Lists.newArrayList();
        ProductOrderItem item1 = new ProductOrderItem();
        item1.setSpecificationId(2);
        item1.setName("test1");
        item1.setProductOrderId(1l);
        item1.setProductId(1l);
        item1.setBusinessId(1l);
        item1.setCartId(1l);
        item1.setPrice(new BigDecimal(12));
        item1.setQuantity(1);
        item1.setFreight(new BigDecimal(12));
        item1.setDigest("");
        item1.setThumbnail("");
        items.add(item1);
        productOrderDao.batchSaveOrderItems(1l,items);
    }
}
