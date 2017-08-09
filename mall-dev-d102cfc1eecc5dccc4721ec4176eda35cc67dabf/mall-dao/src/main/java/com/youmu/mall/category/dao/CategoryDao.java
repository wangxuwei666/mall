package com.youmu.mall.category.dao;

import com.youmu.common.page.utils.Page;
import com.youmu.mall.category.domain.Category;
import com.youmu.mall.category.vo.CategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */
public interface CategoryDao {
    /**
     * 获取所有的分类
     * @return
     */
    CategoryVo getCategoryVo(@Param("id")Integer categoryId);

    /**
     * 通过名字查询
     * @param name
     * @return
     */
    long getCategoryByName(@Param("name") String name);

    /**
     * 添加一个分类
     * @param category
     */
    Integer addCategory( Category category);
}
