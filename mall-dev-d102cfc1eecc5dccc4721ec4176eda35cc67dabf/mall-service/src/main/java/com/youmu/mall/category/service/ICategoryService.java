package com.youmu.mall.category.service;

import com.youmu.mall.category.vo.CategoryVo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */
public interface ICategoryService {
    /**
     * 根据id获取分类vo
     * @return
     */
    CategoryVo getCategoryVo(Integer categoryId);

    /**
     * 添加一个分类
     * @param name 分类名字
     */
    Integer addCategory(String name);

    /**
     * 添加详情
     * @param categoryId  分类id
     * @param spfiName 分类名字
     */
    Integer addSpecification(Integer categoryId,String spfiName);

    /**
     * 删除一个详情
     * @param specificationId
     * @return
     */
    Integer delSpecification(Integer specificationId);
}
