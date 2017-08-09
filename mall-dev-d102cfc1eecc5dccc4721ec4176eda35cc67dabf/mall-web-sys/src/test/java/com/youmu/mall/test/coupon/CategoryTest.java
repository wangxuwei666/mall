package com.youmu.mall.test.coupon;

import com.youmu.mall.category.service.ICategoryService;
import com.youmu.mall.category.vo.CategoryVo;
import com.youmu.mall.product.dao.ProductDao;
import com.youmu.mall.product.domain.Product;
import com.youmu.mall.test.BaseTest;
import org.junit.Test;
import javax.annotation.Resource;

/**
 * Created by shitou on 2017/6/17.
 */
public class CategoryTest extends BaseTest {
    @Resource
    private ICategoryService iCategoryService;
    @Resource private ProductDao productDao;

    @Test
    public void testUser(){
        CategoryVo categoryVo = iCategoryService.getCategoryVo(5);
        System.out.println(categoryVo);
    }

    @Test
    public void updateProduct(){
        Product product = new Product();
        product.setId(201l);
        product.setCategoryId(1);
        product.setTotal(1000);
         productDao.update(product);
        System.out.println("");
    }
}
