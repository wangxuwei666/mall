/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youmu.common.date.utils.DateUtils;
import com.youmu.common.global.constant.StatusConstant;
import com.youmu.common.page.utils.Page;
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.base.query.PageQuery;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.product.dao.ProductDao;
import com.youmu.mall.product.dao.ProductGroupDao;
import com.youmu.mall.product.domain.Product;
import com.youmu.mall.product.domain.ProductGroup;
import com.youmu.mall.product.domain.ProductGroupDetail;
import com.youmu.mall.product.query.ProductGroupQuery;
import com.youmu.mall.product.service.IProductGroupService;
import com.youmu.mall.product.vo.GroupDateVo;
import com.youmu.mall.product.vo.ProductGroupEditVo;
import com.youmu.mall.product.vo.ProductGroupShopVo;
import com.youmu.mall.product.vo.ProductGroupSysVo;
import com.youmu.mall.product.vo.ProductSysVo;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductGroupServiceImpl.java, v 0.1 2017年3月7日 上午11:20:27 yujiahao Exp $
 */
@Service
public class ProductGroupServiceImpl implements IProductGroupService{
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private ProductGroupDao productGroupDao;
    
    @Resource
    private ProductDao productDao;

    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#getProductGroupSysVo(com.youmu.mall.product.query.ProductGroupQuery)
     */
    @Override
    public Page<ProductGroupSysVo> getProductGroupSysVo(ProductGroupQuery query) {
        Page<ProductGroupSysVo> page = new Page<>();
        page.setPageNum(query.getPageNum());
        page.setPageSize(query.getPageSize());
        page.setData(productGroupDao.getProductGroupSysVo(query));
        page.setTotal(productGroupDao.getProductGroupSysVoByCount(query));
        return page;
    }

    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#createProductGroup(com.youmu.mall.product.domain.ProductGroup)
     */
    @Override
    @Transactional
    public void createProductGroup(ProductGroup productGroup) {
        
        ValidateUtils.checkNotNull("参数不能为空", productGroup.getTotalQuantity(),productGroup.getGroupType(),productGroup.getLimitQuantity(),productGroup.getName());
        //业务判断，参数校验
        if(productGroup.getItems().size()==0){
            throw new ParamException("未选择活动组商品项");
        }
        if(productGroup.getItems().size()>20){
            throw new ParamException("商品项不能超过20条");
        }
        if(productGroup.getTotalQuantity()<0){
            throw new ParamException("商品数量不能为负");
        }
        if(productGroup.getLimitQuantity()<0){
            throw new ParamException("用户可购买数量不能为负");
        }
        if(productGroup.getLimitQuantity() > productGroup.getTotalQuantity()){
            throw new ParamException("用户可购买数量不能大于商品总数");
        }
        //如果为秒杀类型的活动
        if(productGroup.getGroupType()==StatusConstant.ONE){
            //验证开始和结束时间非空
            ValidateUtils.checkNotNull("时间不能为空",productGroup.getGmtStart(),productGroup.getGmtEnd());
            //验证开始时间和结束时间不能跨天
            if(!DateUtils.checkSecKillDate(productGroup.getGmtStart(), productGroup.getGmtEnd())){
                throw new ParamException("时间参数不能跨天或相等，请重新设置");
            }
            //当前时间是否可用
            boolean isUsed = productGroupDao.checkDateIsUsed(productGroup.getGmtStart(), productGroup.getGmtEnd());
            if (isUsed) {
                throw new ParamException("当前时间段已经有秒杀活动");
            }
        }
        //如果为特价类型的活动
        if(productGroup.getGroupType()==StatusConstant.TWO){
            //验证图片非空
            ValidateUtils.checkNotNull("图片不能为空",productGroup.getGroupImg());
            productGroup.setGroupImg(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.PRODUCT_GROUP_BANNER, productGroup.getGroupImg()));
        }

        //如果为推荐类型的活动
        if(productGroup.getGroupType()==StatusConstant.THREE){
            //验证图片非空
            ValidateUtils.checkNotNull("图片不能为空",productGroup.getGroupImg());
            productGroup.setGroupImg(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.PRODUCT_GROUP_BANNER, productGroup.getGroupImg()));
        }
        
      //如果为积分类型的活动
        if(productGroup.getGroupType()==StatusConstant.FOUR){
        	//验证图片非空
        	ValidateUtils.checkNotNull("图片不能为空", productGroup.getGroupImg());
            productGroup.setGroupImg(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.PRODUCT_GROUP_BANNER, productGroup.getGroupImg()));
          //验证开始和结束时间非空
            ValidateUtils.checkNotNull("时间不能为空",productGroup.getGmtStart(),productGroup.getGmtEnd());
        }
        
        
        //保存主表
        productGroupDao.insertAndGetId(productGroup);
        
        //判断商品是否正在进行活动
        ArrayList<Long> productIds = new ArrayList<>();
        PageQuery groupQuery = new PageQuery();
        List<ProductSysVo> productSysVos = productGroupDao.getNotReduceProduct(groupQuery);
        for (ProductSysVo productSysVo : productSysVos) {
            productIds.add(productSysVo.getId());
        }
        
        //保存明细
        if(productGroup.getId()!=null){ 
            List<ProductGroupDetail> items = productGroup.getItems();
            
            for (ProductGroupDetail productGroupDetail : items) {
                Long productId = productGroupDetail.getProductId();
                //判断价格和数量不能为负
                if(productGroupDetail.getQuantity()<0){
                    throw new ParamException("活动商品数量不能为负");
                }
                if(productGroupDetail.getPrice().compareTo(ValidateUtils.BIGDECIMAL_ZERO) < 0){
                    throw new ParamException("活动商品价格不能为负");
                }
                if(productId != null){
                    Product product = productDao.getById(productId);
                    Integer totalInStock = product.getTotal();
                    BigDecimal promotionPriceInDB = product.getPromotionPrice();
                    //判断商品的数量是否超过库存数
                    if(totalInStock<productGroupDetail.getQuantity()){
                        throw new ParamException("id为 ： "+ productId + " 的商品数量大于库存，请重新输入本次活动的商品数量");
                    }
                    
                    if(productGroup.getGroupType()!=StatusConstant.FOUR){
                    
                    	//判断商品价格是否超过促销价
                    	if(promotionPriceInDB==null || ValidateUtils.isEqualsByBigDecimal(ValidateUtils.BIGDECIMAL_ZERO, promotionPriceInDB)){
                    	   BigDecimal oldPriceInDB = product.getOldPrice();
                    	   if(ValidateUtils.findLargerOne(productGroupDetail.getPrice(),oldPriceInDB)){
                    		   throw new ParamException("当前商品价格要小于商品原价");
                    	   }
                    	}else {
                    		if (ValidateUtils.findLargerOne(productGroupDetail.getPrice(),promotionPriceInDB)) {
                    			throw new ParamException("当前商品价格要小于商品促销价");
                    			}
                    		}
                    	}
                    //判断当前商品是否可以搞活动
                    if (!productIds.contains(productId)) {
                        throw new ParamException("当前商品已经参与了秒杀或特价活动");
                    }
                    
                }
            }
            productGroupDao.batchSaveGroupItems(productGroup.getItems(), productGroup.getId());
        }else {
            throw new BusinessException("未保存id");
        }
    }

    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#getProductGroupDetailSysVoByGroupId(java.lang.Long)
     */
    @Override
    public ProductGroupEditVo getProductGroupDetailSysVoByGroupId(Long groupId,Integer status) {
        return productGroupDao.getProductGroupDetailSysVoByGroupId(groupId,status);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#getNotReduceProduct(com.youmu.mall.base.query.PageQuery)
     */
    @Override
    public Page<ProductSysVo> getNotReduceProduct(PageQuery query) {
        Page<ProductSysVo> page = new Page<>(query.getPageNum(),query.getPageSize());
        page.setData(productGroupDao.getNotReduceProduct(query));
        page.setTotal(productGroupDao.getCountByNotReduceProduct(query));
        return page;
    }

    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#updateProductGroup(com.youmu.mall.product.domain.ProductGroup)
     */
    @Override
    @Transactional
    public void updateProductGroup(ProductGroup productGroup) {
        
        //业务判断，参数判断
        if(productGroup.getItems().size()==0){
            throw new ParamException("未选择活动组商品项");
        }
        if(productGroup.getItems().size()>20){
            throw new ParamException("商品项不能超过20条");
        }
        ValidateUtils.checkNotNull("参数不能为空", productGroup.getId(),productGroup.getTotalQuantity(),productGroup.getGroupType(),productGroup.getLimitQuantity(),productGroup.getName());
        if(productGroup.getTotalQuantity()<0){
            throw new ParamException("商品数量不能为负");
        }
        if(productGroup.getLimitQuantity()<0){
            throw new ParamException("用户可购买数量不能为负");
        }
        if(productGroup.getLimitQuantity() > productGroup.getTotalQuantity()){
            throw new ParamException("用户可购买数量不能大于商品总数");
        }
        //如果为秒杀类型的活动
        if(productGroup.getGroupType()==StatusConstant.ONE){
            //验证开始和结束时间非空
            ValidateUtils.checkNotNull("时间不能为空",productGroup.getGmtStart(),productGroup.getGmtEnd());
            //验证开始时间和结束时间不能跨天
            if(!DateUtils.checkSecKillDate(productGroup.getGmtStart(), productGroup.getGmtEnd())){
                throw new ParamException("时间参数不能跨天");
            }
        }
        //如果为特价类型的活动
        if(productGroup.getGroupType()==StatusConstant.TWO){
            if(productGroup.getGroupImg() != null){
                //若上传了图片，则修改图片
                productGroup.setGroupImg(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.PRODUCT_GROUP_BANNER, productGroup.getGroupImg()));
            }
        }

        //如果为推荐类型的活动
        if(productGroup.getGroupType()==StatusConstant.THREE){
            if(productGroup.getGroupImg() != null){
                //若上传了图片，则修改图片
                productGroup.setGroupImg(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.PRODUCT_GROUP_BANNER, productGroup.getGroupImg()));
            }
        }
        
        if(productGroup.getGroupType()==StatusConstant.FOUR){
            if(productGroup.getGroupImg() != null){
                //若上传了图片，则修改图片
                productGroup.setGroupImg(ImageUploadUtils.uploadImageDefault(ImageUploadUtils.PRODUCT_GROUP_BANNER, productGroup.getGroupImg()));
            }
        }
        
        //1、修改主表字段
        productGroupDao.update(productGroup);
        
        //2、先获取之前的活动组明细id,过滤已经参加的活动商品
        List<Long> productIds = productGroupDao.selectProductGroupDetailsProductIdsByGroupId(productGroup.getId());
        //删除原来的记录项
        productGroupDao.deleteProductGroupDetailsByGroupId(productGroup.getId());
        
        //3、新增子项
        //判断商品是否正在进行活动,先获取当前未参加活动的商品，
        List<Long> notReduceProductIds = productGroupDao.getNotReduceProductIds();
        notReduceProductIds.addAll(productIds);
        
        //保存明细
        if(productGroup.getId()!=null){
            List<ProductGroupDetail> items = productGroup.getItems();
            
            for (ProductGroupDetail productGroupDetail : items) {
                Long productId = productGroupDetail.getProductId();
                if(productGroupDetail.getQuantity()<0){
                    throw new ParamException("活动商品数量不能为负");
                }
                if(productGroupDetail.getPrice().compareTo(ValidateUtils.BIGDECIMAL_ZERO) < 0){
                    throw new ParamException("活动商品价格不能为负");
                }
                if(productId != null){
                    Product product = productDao.getById(productId);
                    Integer totalInStock = product.getTotal();
                    BigDecimal promotionPriceInDB = product.getPromotionPrice();
                    //判断商品的数量是否超过库存数
                    if(totalInStock<productGroupDetail.getQuantity()){
                        throw new ParamException("id为 ： "+ productId + " 的商品数量大于库存，请重新输入本次活动的商品数量");
                    }
                    //判断商品价格是否超过促销价,积分商品允许价格增加
                    if(productGroup.getGroupType()!=StatusConstant.FOUR){
                    
                    	if(promotionPriceInDB==null || ValidateUtils.isEqualsByBigDecimal(ValidateUtils.BIGDECIMAL_ZERO, promotionPriceInDB)){
                    		BigDecimal oldPriceInDB = product.getOldPrice();
                    		if(ValidateUtils.findLargerOne(productGroupDetail.getPrice(),oldPriceInDB)){
                    			throw new ParamException("当前商品价格要小于商品促销价或原价");
                    		}
                    	}else {
                    		if (ValidateUtils.findLargerOne(productGroupDetail.getPrice(),promotionPriceInDB)) {
                    			throw new ParamException("当前商品价格要小于商品促销价或原价");
                    		}
                    	}
                    }
                    //判断当前商品是否可以搞活动
                    if (!notReduceProductIds.contains(productId)) {
                        throw new ParamException("当前商品已经参与了活动");
                    }
                }else{
                    throw new ParamException("商品id为空");
                }
            }
            productGroupDao.batchSaveGroupItems(productGroup.getItems(), productGroup.getId());
        }else {
            throw new BusinessException("未保存id");
        }
        
    }

    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#deleteProductGroup(java.lang.Long)
     */
    @Override
    public void deleteProductGroup(Long id) {
        //先删除活动明细里面的商品信息
        productGroupDao.deleteProductGroupDetailsByGroupId(id);
        //再删除活动主表的信息
        productGroupDao.deleteProductGroup(id);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#getProductGroupShopVo(com.youmu.mall.product.query.ProductGroupQuery)
     */
    @Override
    public Page<ProductGroupShopVo> getProductGroupShopVo(ProductGroupQuery query) {
        Page<ProductGroupShopVo> page = new Page<>(query.getPageNum(),query.getPageSize());
        page.setData(productGroupDao.getProductGroupShopVo(query));
        page.setTotal(productGroupDao.getCountByProductGroupShopVo(query.getGroupType()));
        return page;
    }
    
    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#getProductGroupGetShopVo(com.youmu.mall.product.query.ProductGroupQuery)
     */
    @Override
    public Page<ProductGroupShopVo> getProductGroupGetShopVo(ProductGroupQuery query) {
        Page<ProductGroupShopVo> page = new Page<>(query.getPageNum(),query.getPageSize());
        page.setData(productGroupDao.getProductGroupGetShopVo(query));
        page.setTotal(productGroupDao.getCountByProductGroupGetShopVo(query));
        return page;
    }
    
    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#getProductGroupPostShopVo(com.youmu.mall.product.query.ProductGroupQuery)
     */
    @Override
    public Page<ProductGroupShopVo> getProductGroupPostShopVo(ProductGroupQuery query) {
        Page<ProductGroupShopVo> page = new Page<>(query.getPageNum(),query.getPageSize());
        page.setData(productGroupDao.getProductGroupPostShopVo(query));
        page.setTotal(productGroupDao.getCountByProductGroupPostShopVo(query));
        return page;
    }

    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#getProductGroupDetailShopVo(java.lang.Long)
     */
    @Override
    public ProductGroupShopVo getProductGroupDetailShopVoById(Long groupId) {
        return productGroupDao.getProductGroupDetailShopVoById(groupId);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductGroupService#getGroupBannerDate()
     */
    @Override
    public List<GroupDateVo> getGroupBannerDate() {
        return productGroupDao.getGroupBannerDate();
    }

}
