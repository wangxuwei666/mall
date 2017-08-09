package com.youmu.mall.category.service.impl;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.category.dao.CategoryDao;
import com.youmu.mall.category.dao.SpecificationDao;
import com.youmu.mall.category.domain.Category;
import com.youmu.mall.category.domain.Specification;
import com.youmu.mall.category.service.ICategoryService;
import com.youmu.mall.category.vo.CategoryVo;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.vo.ProductSysVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private SpecificationDao specificationDao;

    @Override
    public CategoryVo getCategoryVo(Integer categoryId) {
        CategoryVo categoryVo = categoryDao.getCategoryVo(categoryId);
        return categoryVo;
    }

    @Override
    public Integer addCategory(String name) {
       Category category = new Category();
       category.setName(name);
       categoryDao.addCategory(category);
        return category.getId();
    }

    @Override
    public Integer addSpecification(Integer categoryId, String spfiName) {

        Integer count = specificationDao.addSpecifications(categoryId, spfiName);
        return count;
    }

    @Override
    public Integer delSpecification(Integer specificationId) {
        Integer count = specificationDao.delSpecification(specificationId);
        return count;
    }

}
