package com.youmu.mall.category.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */
public interface SpecificationDao {
    Integer addSpecifications(@Param("categoryId")Integer categoryId,@Param("spfiName") String spfiName);

    /**
     * 删除一个分类
     * @param specificationId
     * @return
     */
    Integer delSpecification(@Param("id") Integer specificationId);
}
